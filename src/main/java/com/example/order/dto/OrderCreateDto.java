package com.example.order.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class OrderCreateDto {
    Timestamp bookingAt;
    String orderDescription;
    Long productId;
    BigDecimal productPrice;
    BigDecimal productQuantity;
    String deliveryAddress;
}
