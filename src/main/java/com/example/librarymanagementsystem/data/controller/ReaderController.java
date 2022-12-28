package com.example.librarymanagementsystem.data.controller;

import com.example.librarymanagementsystem.data.dtos.requests.LoginRequest;
import com.example.librarymanagementsystem.data.dtos.requests.RegisterRequest;
import com.example.librarymanagementsystem.data.dtos.requests.UpdateUserDetails;
import com.example.librarymanagementsystem.data.dtos.responses.UpdateUserDetailsResponse;
import com.example.librarymanagementsystem.exceptions.LibrarySystemException;
import com.example.librarymanagementsystem.services.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ReaderController {
    private final ReaderService readerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) throws LibrarySystemException {
        return ResponseEntity.status(HttpStatus.CREATED).body(readerService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws LibrarySystemException{
        return ResponseEntity.status(HttpStatus.CREATED).body(readerService.login(loginRequest));
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateUserDetails updateRequest) throws LibrarySystemException {
        UpdateUserDetailsResponse response = readerService.updateProfile(updateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(readerService.updateProfile(updateRequest));
    }


}
