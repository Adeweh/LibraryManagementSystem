package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.data.dtos.requests.LoginRequest;
import com.example.librarymanagementsystem.data.dtos.requests.RegisterRequest;
import com.example.librarymanagementsystem.data.dtos.requests.UpdateUserDetails;
import com.example.librarymanagementsystem.data.dtos.responses.LoginResponse;
import com.example.librarymanagementsystem.data.dtos.responses.RegisterResponse;
import com.example.librarymanagementsystem.data.models.Address;
import com.example.librarymanagementsystem.data.models.LibraryUser;
import com.example.librarymanagementsystem.exceptions.LibrarySystemException;
import com.example.librarymanagementsystem.repository.LibraryUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class LibraryUserServiceImpl implements LibraryUserService{
    private final LibraryUserRepository libraryUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ModelMapper mapper;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        Optional<LibraryUser> user = libraryUserRepository.findByEmail(registerRequest.getEmail());
        if(user.isPresent())
            throw new LibrarySystemException("User dey abeg");

        LibraryUser libraryUser = mapper.map(registerRequest, LibraryUser.class);
        String encodedPassword = bCryptPasswordEncoder.encode(libraryUser.getPassword());
        libraryUser.setPassword("");

        LibraryUser savedUser = libraryUserRepository.save(libraryUser);
        RegisterResponse response = new RegisterResponse("User"+savedUser.getFirstName()+" registered");
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<LibraryUser> userLogin = libraryUserRepository.findByEmail(loginRequest.getEmail());
        if(userLogin.isPresent() && userLogin.get().getPassword().equals(loginRequest.getPassword()))
            return LoginResponse.builder().message("User logged in successfully").build();

        return LoginResponse.builder().message("Login successful.").build();
    }

    @Override
    public String updateProfile(UpdateUserDetails updateDetails) {
        LibraryUser userUpdate = libraryUserRepository.findByEmail(updateDetails.getEmail()).orElseThrow(() -> new  LibrarySystemException
                (String.format("User with email %s, not found", updateDetails.getEmail())));
//        mapper.map(updateDetails, userUpdate);

        Set<Address> userAddressList = userUpdate.getAddresses();

        Optional<Address> foundAddress = userAddressList.stream().findFirst();
        foundAddress.ifPresent(address -> applyAddressUpdate(address, updateDetails));

//        userUpdate.getAddresses().add(foundAddress.get());
//        LibraryUser updatedUser =
            libraryUserRepository.save(userUpdate);



        return String.format("%s details updated successfully", userUpdate.getFirstName());
    }

    private void applyAddressUpdate(Address address, UpdateUserDetails updateDetails) {
        address.setCity(updateDetails.getCity());
        address.setBuildingNumber(updateDetails.getBuildingNumber());
        address.setState(updateDetails.getState());
        address.setStreet(updateDetails.getStreet());
    }
}
