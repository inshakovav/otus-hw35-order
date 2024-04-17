package com.example.order.dto;

import lombok.Data;

@Data
public class OrderCreatedMessage {
    private Long orderId;
    private String orderName;
}
