package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.data.dtos.requests.LoginRequest;
import com.example.librarymanagementsystem.data.dtos.requests.RegisterRequest;
import com.example.librarymanagementsystem.data.dtos.requests.UpdateUserDetails;
import com.example.librarymanagementsystem.data.dtos.responses.LoginResponse;
import com.example.librarymanagementsystem.data.dtos.responses.RegisterResponse;
import com.example.librarymanagementsystem.data.models.Address;
import com.example.librarymanagementsystem.data.models.Reader;
import com.example.librarymanagementsystem.exceptions.LibrarySystemException;
import com.example.librarymanagementsystem.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepository readerRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ModelMapper mapper;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        Optional<Reader> user = readerRepository.findByEmail(registerRequest.getEmail());
        if(user.isPresent())
            throw new LibrarySystemException("User dey abeg");

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

        return LoginResponse.builder().message("Login successful.").build();
    }

    @Override
    public String updateProfile(UpdateUserDetails updateDetails) {
        Reader userUpdate = readerRepository.findByEmail(updateDetails.getEmail()).orElseThrow(() -> new  LibrarySystemException
                (String.format("User with email %s, not found", updateDetails.getEmail())));
//        mapper.map(updateDetails, userUpdate);

        Set<Address> userAddressList = userUpdate.getAddresses();

        Optional<Address> foundAddress = userAddressList.stream().findFirst();
        foundAddress.ifPresent(address -> applyAddressUpdate(address, updateDetails));

//        userUpdate.getAddresses().add(foundAddress.get());
//        Reader updatedUser =
            readerRepository.save(userUpdate);



        return String.format("%s details updated successfully", userUpdate.getFirstName());
    }

    @Override
    public void deleteAll() {
        readerRepository.deleteAll();
    }

    private void applyAddressUpdate(Address address, UpdateUserDetails updateDetails) {
        address.setCity(updateDetails.getCity());
        address.setBuildingNumber(updateDetails.getBuildingNumber());
        address.setState(updateDetails.getState());
        address.setStreet(updateDetails.getStreet());
    }
}
