package com.simple_book_api.book_RestApi;

import com.simple_book_api.book_RestApi.book.domain.Book;
import com.simple_book_api.book_RestApi.book.domain.BookEntity;

public final class TestData {
    private TestData(){

    }

    public static Book testBook(){
        return Book.builder()
                .isbn("12345678")
                .title("Software Dev 101")
                .author("John Doe")
                .build();
    }

    public static BookEntity testBookEntity(){
        return BookEntity.builder()
                .isbn("12345678")
                .title("Software Dev 101")
                .author("John Doe")
                .build();

    }
}
