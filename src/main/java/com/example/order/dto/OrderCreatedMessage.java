package com.example.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCreatedMessage {
    private Long orderId;
    private String orderDescription;
    private Long productId;
    private BigDecimal productPrice;
    private BigDecimal productQuantity;
    private String deliveryAddress;
}
