package com.example.order;

import com.example.order.entity.OrderEntity;
import com.example.order.entity.OrderStatus;
import com.example.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@Slf4j
@ActiveProfiles("local")
class OrderApplicationTests {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void contextLoads() {
        List<OrderEntity> userEntities = orderRepository.findAll();
        log.info("Read users={}", userEntities);

        OrderEntity order = new OrderEntity();
        order.setName("fistname");
        order.setStatus(OrderStatus.PENDING);
        orderRepository.save(order);
        log.info("New order saved");

        List<OrderEntity> userEntities1 = orderRepository.findAll();
        log.info("Read users={}", userEntities1);
    }

}
