package com.example.order.service;

import com.example.order.dto.DbToKafkaMapper;
import com.example.order.dto.OrderCreatedMessage;
import com.example.order.dto.OrderCreateDto;
import com.example.order.dto.RestDBMapper;
import com.example.order.entity.OrderEntity;
import com.example.order.entity.OrderStatus;
import com.example.order.kafka.KafkaProducerService;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final KafkaProducerService kafkaProducerService;
    private final RestDBMapper restDBMapper;
    private final DbToKafkaMapper dbToKafkaMapper;

    public OrderEntity process(OrderCreateDto dto) {
        log.info("Create order: {}", dto);
        OrderEntity dbEntity = saveNew(dto);
        sendCreatedMessage(dbEntity);
        return dbEntity;
    }

    private void sendCreatedMessage(OrderEntity dbEntity) {
        OrderCreatedMessage orderCreatedMessage = dbToKafkaMapper.dbToKafka(dbEntity);
        kafkaProducerService.sendOrder(orderCreatedMessage);
    }

    public OrderEntity saveNew(OrderCreateDto dto) {
        OrderEntity entity = restDBMapper.restToDb(dto);
        entity.setStatus(OrderStatus.PENDING);
        OrderEntity dbEntity = repository.save(entity);
        return dbEntity;
    }
}
