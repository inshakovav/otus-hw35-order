package com.example.order.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class OrderCreateDto {
    Long accountId;
    BigDecimal price;
    Timestamp bookingAt;
}
