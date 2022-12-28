package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.data.dtos.requests.LoginRequest;
import com.example.librarymanagementsystem.data.dtos.requests.RegisterRequest;
import com.example.librarymanagementsystem.data.dtos.requests.UpdateUserDetails;
import com.example.librarymanagementsystem.data.dtos.responses.LoginResponse;
import com.example.librarymanagementsystem.data.dtos.responses.RegisterResponse;
import com.example.librarymanagementsystem.data.dtos.responses.UpdateUserDetailsResponse;
import com.example.librarymanagementsystem.data.models.Reader;
import com.example.librarymanagementsystem.data.models.Role;
import com.example.librarymanagementsystem.exceptions.BookNotFoundException;
import com.example.librarymanagementsystem.exceptions.LibrarySystemException;
import com.example.librarymanagementsystem.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService, UserDetailsService {
    private final ReaderRepository readerRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ModelMapper mapper;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        Optional<Reader> user = readerRepository.findByEmail(registerRequest.getEmail());
        if(user.isPresent())
            throw new LibrarySystemException("User dey abeg", 404);

        Reader reader = mapper.map(registerRequest, Reader.class);
        String encodedPassword = bCryptPasswordEncoder.encode(reader.getPassword());
        reader.setPassword(encodedPassword);

        Reader savedUser = readerRepository.save(reader);
        RegisterResponse response = new RegisterResponse("User"+savedUser.getFirstName()+" registered");
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Reader> userLogin = readerRepository.findByEmail(loginRequest.getEmail());
        if(userLogin.isPresent() && userLogin.get().getPassword().equals(loginRequest.getPassword()))
            return LoginResponse.builder().message("User logged in successfully").build();

        return LoginResponse.builder().message("user with "+loginRequest.getEmail()+"not found").build();
    }

    @Override
    public UpdateUserDetailsResponse updateProfile(UpdateUserDetails updateDetails) {
        Optional<Reader> user = readerRepository.findByEmail(updateDetails.getEmail());
        if(user.isPresent()){
            Reader existingReader = user.get();
            applyAddressUpdate(existingReader, updateDetails);
            readerRepository.save(existingReader);
            return UpdateUserDetailsResponse.builder()
                    .message(existingReader.getFirstName()+ "profile updated successfully")
                    .build();
        }
        throw new LibrarySystemException("Library user with email"+updateDetails.getEmail()+"" +
                "does not exist.", 404 );


    }

    private void applyAddressUpdate(Reader reader, UpdateUserDetails updateDetails) {
        reader.setCity(updateDetails.getCity());
        reader.setBuildingNumber(updateDetails.getBuildingNumber());
        reader.setState(updateDetails.getState());
        reader.setStreet(updateDetails.getStreet());
    }
    @Override
    public void deleteAll() {
        readerRepository.deleteAll();
    }

    @Override
    public void deleteUser(String email) {
        Optional<Reader> deleteUser = readerRepository.findByEmail(email);
        if(deleteUser.isEmpty())
            throw new BookNotFoundException("E no dey");
        readerRepository.delete(deleteUser.get());

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader user = readerRepository.findByEmail(username).orElse(null);
        if(user != null){
            return new User(user.getEmail(), user.getPassword(), getRoles(user.getRoles()));
        }
        throw new LibrarySystemException(username+"email doesnt exist", 404);
    }

    private Collection<? extends GrantedAuthority> getRoles(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(
                role.getAuthorities().toString())).collect(Collectors.toSet());
    }

}
