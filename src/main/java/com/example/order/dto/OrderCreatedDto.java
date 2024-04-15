package com.example.order.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderCreatedDto {
    private Long id;
    private String name;
}
