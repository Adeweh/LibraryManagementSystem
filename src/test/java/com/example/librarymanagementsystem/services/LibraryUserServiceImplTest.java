package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.data.dtos.requests.LoginRequest;
import com.example.librarymanagementsystem.data.dtos.requests.RegisterRequest;
import com.example.librarymanagementsystem.data.dtos.requests.UpdateUserDetails;
import com.example.librarymanagementsystem.data.dtos.responses.LoginResponse;
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
    private LoginRequest loginRequest;

    @BeforeEach
    void setup(){
//        user = LibraryUser.builder().firstName("Dee")
//                .build();
        registerRequest = RegisterRequest.builder().
                firstName("Jummy").lastName("Ade").email("ade@email.com")
                .password("password1234").build();

        loginRequest = LoginRequest.builder().email("ade@email.com").password("password2345").build();
    }

    @Test
    void registerUserTest(){
        RegisterResponse registerResponse = libraryUserService.register(registerRequest);
        assertThat(registerResponse).isNotNull();
    }

    @Test
    void loginUserTest(){

        LoginResponse loginResponse = libraryUserService.login(loginRequest);
        assertThat(loginResponse).isNotNull();

    }

    @Test
    void updateUserProfileTest(){
        libraryUserService.register(registerRequest);
        UpdateUserDetails details = UpdateUserDetails.builder().email(registerRequest.getEmail()).
                city("Kaduna").street("Makaranta")
                .state("Kaduna").phoneNumber("7777")
                .build();

        var updateResponse = libraryUserService.updateProfile(details);
        assertThat(updateResponse).isNotNull();
        assertThat(updateResponse.contains("success")).isTrue();



    }



}
