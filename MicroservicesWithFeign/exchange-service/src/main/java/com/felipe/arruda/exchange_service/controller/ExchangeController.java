package com.felipe.arruda.exchange_service.controller;

import com.felipe.arruda.exchange_service.model.Exchange;
import com.felipe.arruda.exchange_service.service.ExchangeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("exchange-service")
@Tag(name = "Exchange Endpoin")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @Operation(summary = "Get an exchange form amount of currency")
    @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    private Exchange getExchange(@PathVariable BigDecimal amount,
                                 @PathVariable String from,
                                 @PathVariable String to) {
        return exchangeService.getExchange(from, to, amount);
    }

}