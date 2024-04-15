package com.example.order.kafka;

import com.example.order.dto.OrderCreatedDto;
import com.example.order.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send("my-topic", message);
    }

    public void sendMessage2(String message) {
        kafkaTemplate.send("my-topic2", message);
    }

    public void sendOrder(OrderCreatedDto message) {
        kafkaTemplate.send("my-topic2", message);
    }
}
