package com.felipe.arruda.book_service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("book-service")
@Tag(name = "Foo Bar Endpoint")
public class FooBarController {

    private Logger logger = LoggerFactory.getLogger(FooBarController.class);

    @GetMapping("/foo-bar")
    //@Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
    @CircuitBreaker(name = "foo-bar", fallbackMethod = "fallbackMethod")
    public String fooBar() {
        logger.info("Request to foo-bar is received!!");

        ResponseEntity<String> response = new RestTemplate()
                .getForEntity("http://localhost:8000/book-service", String.class);

        return response.getBody();
    }

    public String fallbackMethod(Exception exception) {
        return "fallbackMethod foo-bar!!!";
    }

}