package com.felipe.arruda.book_service.service;

import com.felipe.arruda.book_service.model.Book;

import java.util.Optional;

public interface BookService {

   Book findBook(Long id, String currency);
}
