package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.data.dtos.requests.RegisterRequest;
import com.example.librarymanagementsystem.data.dtos.responses.RegisterResponse;
import com.example.librarymanagementsystem.data.models.LibraryUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class LibraryUserServiceImplTest {
    @Autowired
    private LibraryUserService libraryUserService;
//    private LibraryUser user;

    private RegisterRequest registerRequest;

    @BeforeEach
    void setup(){
//        user = LibraryUser.builder().firstName("Dee")
//                .build();
        registerRequest = RegisterRequest.builder().
                firstName("Jummy").lastName("Ade").email("ade@email.com")
                .password("password1234").build();
    }

    @Test
    void registerUserTest(){
        RegisterResponse registerResponse = libraryUserService.register(registerRequest);
        assertThat(registerResponse).isNotNull();
    }



}
