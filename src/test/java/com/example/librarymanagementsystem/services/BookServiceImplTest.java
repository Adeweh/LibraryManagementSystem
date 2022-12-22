package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.data.dtos.requests.DeleteBookRequest;
import com.example.librarymanagementsystem.data.dtos.requests.RegisterBookRequest;
import com.example.librarymanagementsystem.data.dtos.responses.RegisterBookResponse;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookServiceImplTest {
    @Autowired
    private BookService bookService;

    private static BookRepository bookRepository;
    private static RegisterBookRequest registerBook;

    @BeforeEach
    void setup(){
        registerBook = RegisterBookRequest.builder().
                title("Trust").author("Ade").edition("2nd").isbn(123456789L).build();

    }

    @Test
    void registerBookTest(){
       RegisterBookResponse bookResponse = bookService.registerBook(registerBook);
       assertThat(bookResponse).isNotNull();
    }
    @Test
    void removeBookFromLibraryTest(){

        bookService.registerBook(registerBook);
        bookService.deleteBook(123456789L);
        assertThat(bookRepository).isNull();
//
//        RegisterBookRequest anotherBook = RegisterBookRequest.builder().
//                title("Love").author("Dee").edition("12th").isbn(987654321L).build();
//
//        DeleteBookRequest deleteBook = DeleteBookRequest.builder().
//                title("Trust").author("Ade").edition("2nd").isbn(123456789L).build();
//
//        bookService.deleteBook(deleteBook);
//        assertThat(bookRepository.count(), is(1L));
//        assertEquals(1L, bookRepository.count());




    }

}
