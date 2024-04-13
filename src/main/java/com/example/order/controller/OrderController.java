package com.example.order.controller;

import com.example.order.dto.OrderDto;
import com.example.order.entity.OrderEntity;
import com.example.order.entity.OrderStatus;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @GetMapping
    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    @PostMapping
    public OrderEntity add(@RequestBody OrderDto dto) {
        OrderEntity dbEntity = orderService.process(dto);
        return dbEntity;
    }
}
