package com.felipe.arruda.exchange_service.service;

import com.felipe.arruda.exchange_service.environment.InstanceInformationService;
import com.felipe.arruda.exchange_service.model.Exchange;
import com.felipe.arruda.exchange_service.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Override
    public Exchange getExchange(String from, String to, BigDecimal amount) {
        Exchange exchange = findExchange(from, to);

        BigDecimal conversionFactor = exchange.getConversionFactor();
        BigDecimal convertedValue = amount.multiply(conversionFactor);
        exchange.setConvertedValue(convertedValue);
        exchange.setEnvironment("Port: " + informationService.getServerPort());

        return exchange;
    }

    private Exchange findExchange(String from, String to) {
        Optional<Exchange> exchange = exchangeRepository.findByFromAndTo(from, to);

        return exchange.orElseThrow(() -> new RuntimeException("Currency Unsupported!"));
    }

}