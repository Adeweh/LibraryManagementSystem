package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.data.dtos.requests.LoginRequest;
import com.example.librarymanagementsystem.data.dtos.requests.RegisterRequest;
import com.example.librarymanagementsystem.data.dtos.requests.UpdateUserDetails;
import com.example.librarymanagementsystem.data.dtos.responses.LoginResponse;
import com.example.librarymanagementsystem.data.dtos.responses.RegisterResponse;
import com.example.librarymanagementsystem.repository.ReaderRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class ReaderServiceImplTest {
    @Autowired
    private ReaderService readerService;

    @Autowired
    private ReaderRepository readerRepository;
    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    RegisterResponse registerResponse;

    @BeforeEach
    void setup() throws UnirestException {
        registerRequest = RegisterRequest.builder().
                firstName("Jummy").lastName("Ade").email("adewehabang@gmail.com")
                .password("password1234").build();

        loginRequest = LoginRequest.builder().email("ade@email.com").password("password2345").build();
        registerResponse = readerService.register(registerRequest);
    }
    @AfterEach
    void tearDown(){

        readerService.deleteAll();
    }

    @Test
    void registerUserTest() {
        assertThat(registerResponse).isNotNull();

    }
    @Test
    void sendEmailTest(){


    }

    @Test
    void loginUserTest() {

        LoginResponse loginResponse = readerService.login(loginRequest);
        assertThat(loginResponse).isNotNull();

    }

    @Test
    void updateUserProfileTest() {

        UpdateUserDetails details = UpdateUserDetails.builder().email(registerRequest.getEmail()).
                city("Kaduna").street("Makaranta")
                .state("Kaduna").phoneNumber("7777")
                .build();

        var updateResponse = readerService.updateProfile(details);
        assertThat(updateResponse).isNotNull();
//        assertThat(updateResponse.contains("success")).isTrue();

    }

    @Test
    void removeUserTest(){
       readerService.deleteUser("ade@email.com");
       assertTrue(readerRepository.findAll().isEmpty());

    }


}
