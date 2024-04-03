package com.simple_book_api.book_RestApi.book.service;

import java.util.List;
import java.util.Optional;

import com.simple_book_api.book_RestApi.book.domain.Book;

public interface IBookService {

    boolean isBookExits(Book book);

    Book save(Book book);
    Optional<Book> findByid(String isbn);
    List<Book> listBook();

    void deleteBookById(String isbn);

}
