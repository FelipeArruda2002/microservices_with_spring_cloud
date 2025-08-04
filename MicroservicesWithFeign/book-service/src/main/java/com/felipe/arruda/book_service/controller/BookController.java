package com.felipe.arruda.book_service.controller;

import com.felipe.arruda.book_service.model.Book;
import com.felipe.arruda.book_service.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book-service")
@Tag(name = "Book Endpoint")
public class BookController {

    @Autowired
    private BookService bookService;

    @Operation(summary = "Find a specific book by your ID")
    @GetMapping("{id}/{currency}")
    public Book findBook(@PathVariable Long id, @PathVariable String currency) {
        return bookService.findBook(id, currency);
    }

}