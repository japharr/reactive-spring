package com.japharr.reactivedemo.controller;

import com.japharr.reactivedemo.model.Book;
import com.japharr.reactivedemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{isbn}")
    private Mono<Book> getBookByIsbn(@PathVariable String isbn) {

        Book book = bookRepository.findBookByIsbn(isbn);

        Mono<Book> just = Mono.just(book);

        return just;
    }

    @GetMapping
    private Flux<Object> getAllBooks() {
        List<Book> list = bookRepository.findAllBooks();

        Flux<Object> just = Flux.fromArray(list.toArray());

        return just;
    }
}
