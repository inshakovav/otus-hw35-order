package com.example.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCreateDto {
    String orderDescription;
    Long productId;
    BigDecimal productPrice;
    BigDecimal productQuantity;
    String deliveryAddress;
}
