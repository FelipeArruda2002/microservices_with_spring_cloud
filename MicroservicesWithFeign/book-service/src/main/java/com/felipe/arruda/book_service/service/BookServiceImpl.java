package com.felipe.arruda.book_service.service;

import com.felipe.arruda.book_service.dto.Exchange;
import com.felipe.arruda.book_service.environment.InstanceInformationService;
import com.felipe.arruda.book_service.model.Book;
import com.felipe.arruda.book_service.proxy.ExchangeProxy;
import com.felipe.arruda.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private InstanceInformationService informationService;
    @Autowired
    private BookRepository repository;
    @Autowired
    private ExchangeProxy exchangeProxy;

    @Override
    public Book findBook(Long id, String currency) {
        Book book = findBookById(id);

        BigDecimal priceBook = book.getPrice();

        Exchange exchange = exchangeProxy.getExchange(priceBook, "USD", currency);
        book.setPrice(exchange.convertedValue());
        book.setCurrency(currency);
        book.setEnvironment("Port: " + informationService.getServerPort());

        return book;
    }

    private Book findBookById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

}