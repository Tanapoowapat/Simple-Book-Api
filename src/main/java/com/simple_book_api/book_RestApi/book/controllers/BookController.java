package com.simple_book_api.book_RestApi.book.controllers;

import com.simple_book_api.book_RestApi.book.domain.Book;
import com.simple_book_api.book_RestApi.book.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private final IBookService bookService;

    @Autowired
    public BookController(final IBookService bookService){
        this.bookService = bookService;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<Book> createUpdateBook(@PathVariable final String isbn,
                                                 @RequestBody final Book book) {
        book.setIsbn(isbn);
        final boolean isBookExits = bookService.isBookExits(book);
        final Book savedBook = bookService.save(book);


        if(isBookExits){
            return new ResponseEntity<Book>(savedBook, HttpStatus.OK);
        }else{
            return new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<Book> getBookById(@PathVariable final String isbn){
        final Optional<Book> foundBook = bookService.findByid(isbn);
        return foundBook.map(book -> new ResponseEntity<Book>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<Book>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/books")
    public ResponseEntity <List<Book>> getAllBook(){
        return new ResponseEntity <List<Book>> (bookService.listBook(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity deleteBook(@PathVariable final String isbn){
        bookService.deleteBookById(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
