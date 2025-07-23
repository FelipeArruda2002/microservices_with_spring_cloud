package com.felipe.arruda.exchange_service.service;

import com.felipe.arruda.exchange_service.model.Exchange;

import java.math.BigDecimal;

public interface ExchangeService {

    Exchange getExchange(String from, String to, BigDecimal amount);
}
