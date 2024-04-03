package com.simple_book_api.book_RestApi.book.service.impl;

import com.simple_book_api.book_RestApi.book.domain.Book;
import com.simple_book_api.book_RestApi.book.domain.BookEntity;
import com.simple_book_api.book_RestApi.book.repositories.IBookRepositories;
import com.simple_book_api.book_RestApi.book.service.IBookService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService implements IBookService {

    private final IBookRepositories bookRepo;

    @Autowired
    public BookService(final IBookRepositories bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public boolean isBookExits(Book book) {
        return bookRepo.existsById(book.getIsbn());
    }

    @Override
    public Book save(final Book book) {
        final BookEntity bookEntity = bookToBookEntity(book);
        final BookEntity saveBook = bookRepo.save(bookEntity);
        return bookEntityToBook(saveBook);
    }

    private BookEntity bookToBookEntity(Book book) {
        return BookEntity.builder()
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .title(book.getTitle())
                .build();
    }

    private Book bookEntityToBook(BookEntity bookEntity) {
        return Book.builder()
                .isbn(bookEntity.getIsbn())
                .author(bookEntity.getAuthor())
                .title(bookEntity.getTitle())
                .build();
    }

    @Override
    public Optional<Book> findByid(String isbn) {
        final Optional<BookEntity> foundBook = bookRepo.findById(isbn);
        return foundBook.map(this::bookEntityToBook);
    }

    @Override
    public List<Book> listBook() {
        final List<BookEntity> books = bookRepo.findAll();
        return books.stream().map(this::bookEntityToBook).collect(Collectors.toList());
    }

    @Override
    public void deleteBookById(String isbn) {
        try{
            bookRepo.deleteById(isbn);
        }catch (final EmptyResultDataAccessException ex){
            log.debug("Books Not Found: ", ex);
        }

    }

}
