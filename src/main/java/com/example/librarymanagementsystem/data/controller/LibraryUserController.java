package com.example.librarymanagementsystem.data.controller;

import com.example.librarymanagementsystem.data.dtos.requests.RegisterRequest;
import com.example.librarymanagementsystem.exceptions.LibrarySystemException;
import com.example.librarymanagementsystem.services.LibraryUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LibraryUserController {
    private final LibraryUserService libraryUserService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) throws LibrarySystemException {
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryUserService.register(request));
    }



}
