package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.data.dtos.requests.RegisterRequest;
import com.example.librarymanagementsystem.data.dtos.responses.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface LibraryUserService {
    RegisterResponse register(RegisterRequest registerRequest);
}
