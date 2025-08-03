package com.felipe.arruda.book_service.proxy;

import com.felipe.arruda.book_service.dto.Exchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "exchange-service")
public interface ExchangeProxy {

    @GetMapping(value = "/exchange-service/{amount}/{from}/{to}")
    public Exchange getExchange(@PathVariable BigDecimal amount,
                                @PathVariable String from,
                                @PathVariable String to);

}
