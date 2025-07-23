package com.felipe.arruda.book_service.dto;

import java.math.BigDecimal;

public record Exchange(String from, String to, BigDecimal convertedValue) {
}
