package com.simple_book_api.book_RestApi.book.repositories;

import com.simple_book_api.book_RestApi.book.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IBookRepositories extends JpaRepository<BookEntity, String>, PagingAndSortingRepository<BookEntity, String> {
}

