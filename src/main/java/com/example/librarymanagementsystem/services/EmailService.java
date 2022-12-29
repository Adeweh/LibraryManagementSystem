package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.data.dtos.requests.MailRequest;
import com.example.librarymanagementsystem.data.dtos.responses.MailResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.concurrent.CompletableFuture;

public interface EmailService {
    CompletableFuture<MailResponse> sendSimpleMail (MailRequest mailRequest) throws UnirestException;
}
