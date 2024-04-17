package com.example.order.service;

import com.example.order.dto.OrderCreatedMessage;
import com.example.order.dto.OrderCreateDto;
import com.example.order.entity.OrderEntity;
import com.example.order.entity.OrderStatus;
import com.example.order.kafka.KafkaProducerService;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final KafkaProducerService kafkaProducerService;

    public OrderEntity process(OrderCreateDto dto) {
        OrderEntity dbEntity = addNew(dto);

        OrderCreatedMessage orderCreatedMessage = new OrderCreatedMessage();
        orderCreatedMessage.setOrderId(dbEntity.getId());
        orderCreatedMessage.setOrderDescription(dbEntity.getDescription());
        kafkaProducerService.sendOrder(orderCreatedMessage);
        return dbEntity;
    }

    public OrderEntity addNew(OrderCreateDto dto) {
        OrderEntity entity = new OrderEntity();
        entity.setDescription(dto.getOrderDescription());
        entity.setStatus(OrderStatus.PENDING);
        OrderEntity dbEntity = repository.save(entity);
        return dbEntity;
    }
}
