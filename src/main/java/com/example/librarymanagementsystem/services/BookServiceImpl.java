package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.data.dtos.requests.DeleteBookRequest;
import com.example.librarymanagementsystem.data.dtos.requests.RegisterBookRequest;
import com.example.librarymanagementsystem.data.dtos.responses.RegisterBookResponse;
import com.example.librarymanagementsystem.data.models.Book;
import com.example.librarymanagementsystem.exceptions.BookNotFoundException;
import com.example.librarymanagementsystem.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;
    private final ModelMapper mapper;

    @Override
    public RegisterBookResponse registerBook(RegisterBookRequest bookRequest) {
        Optional<Book> book = bookRepository.findBookByIsbn(bookRequest.getIsbn());
        if(book.isPresent())
            throw new BookNotFoundException("E don dey before");

        Book newBook = mapper.map(bookRequest, Book.class);
        Book savedBook = bookRepository.save(newBook);

        RegisterBookResponse bookResponse = new RegisterBookResponse("User"+savedBook.getAuthor()+"registered");
        return bookResponse;

    }

    @Override
    public void deleteBook(long isbn) {
        Optional<Book> book = bookRepository.findBookByIsbn(isbn);
        if(book.isEmpty())
            throw new BookNotFoundException("E no dey");
        bookRepository.delete(book.get());


    }

}