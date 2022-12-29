package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.data.dtos.requests.LoginRequest;
import com.example.librarymanagementsystem.data.dtos.requests.RegisterRequest;
import com.example.librarymanagementsystem.data.dtos.requests.UpdateUserDetails;
import com.example.librarymanagementsystem.data.dtos.responses.LoginResponse;
import com.example.librarymanagementsystem.data.dtos.responses.RegisterResponse;
import com.example.librarymanagementsystem.data.dtos.responses.UpdateUserDetailsResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public interface ReaderService {
    RegisterResponse register(RegisterRequest registerRequest) throws UnirestException;

    LoginResponse login(LoginRequest loginRequest);

    UpdateUserDetailsResponse updateProfile(UpdateUserDetails details);

    void deleteAll();

    void deleteUser(String email);
}
