package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.data.dtos.requests.DeleteBookRequest;
import com.example.librarymanagementsystem.data.dtos.requests.RegisterBookRequest;
import com.example.librarymanagementsystem.data.dtos.responses.RegisterBookResponse;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    RegisterBookResponse registerBook(RegisterBookRequest registerRequest);

    void deleteBook(long isbn);
}
