package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.data.dtos.requests.RegisterRequest;
import com.example.librarymanagementsystem.data.dtos.responses.RegisterResponse;
import com.example.librarymanagementsystem.data.models.LibraryUser;
import com.example.librarymanagementsystem.exceptions.LibrarySystemException;
import com.example.librarymanagementsystem.repository.LibraryUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
