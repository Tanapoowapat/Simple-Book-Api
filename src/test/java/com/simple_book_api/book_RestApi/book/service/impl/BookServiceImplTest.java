package com.simple_book_api.book_RestApi.book.service.impl;

import com.simple_book_api.book_RestApi.TestData;
import com.simple_book_api.book_RestApi.book.domain.Book;
import com.simple_book_api.book_RestApi.book.domain.BookEntity;
import com.simple_book_api.book_RestApi.book.repositories.IBookRepositories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
    @Mock
    private IBookRepositories bookRepository;

    @InjectMocks
    private BookService underTest;

    @Test
    public void testThatBooksSave(){
        final Book book = TestData.testBook();


        final BookEntity bookEntity = TestData.testBookEntity();

        when(bookRepository.save(eq(bookEntity))).thenReturn(bookEntity);

        final Book result = underTest.save(book);
        assertEquals(book, result);

    }

    @Test
    public void testThatFindByIdReturnEmptyWhenNoBook(){
        final String isbn = "12341234";
        when(bookRepository.findById(eq(isbn))).thenReturn(Optional.empty());
        final Optional<Book> result = underTest.findByid(isbn);
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testThatFindByIdReturnBook(){
        final Book book = TestData.testBook();
        final BookEntity bookEntity = TestData.testBookEntity();
        when(bookRepository.findById(eq(book.getIsbn()))).thenReturn(Optional.of(bookEntity));
        final Optional<Book> result = underTest.findByid(book.getIsbn());
        assertEquals(Optional.of(book), result);
    }

    @Test
    public void testThatGetAllBookReturnEmptyList(){
        when(bookRepository.findAll()).thenReturn(new ArrayList<BookEntity>());
        final List<Book> result = underTest.listBook();
        assertEquals(0, result.size());
    }

    @Test
    public void testThatGetAllBookReturnBookList(){
        final BookEntity bookEntity = TestData.testBookEntity();
        when(bookRepository.findAll()).thenReturn(List.of(bookEntity));
        final List<Book> result = underTest.listBook();
        assertEquals(1, result.size());
    }

    @Test
    public void testThatBookExitsReturnFalse(){
        when(bookRepository.existsById(any())).thenReturn(false);
        final boolean result = underTest.isBookExits(TestData.testBook());
        assertEquals(false, result);
    }

    @Test
    public void testThatBookExitsReturnTrue(){
        when(bookRepository.existsById(any())).thenReturn(true);
        final boolean result = underTest.isBookExits(TestData.testBook());
        assertEquals(true, result);
    }

    @Test
    public void testThatBookDelete(){
        final String isbn = "12345678";
        underTest.deleteBookById(isbn);
        verify(bookRepository, times(1)).deleteById(eq(isbn));
    }
}
