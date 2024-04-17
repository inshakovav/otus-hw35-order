package com.example.order.kafka;

import com.example.order.dto.OrderCreatedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrder(OrderCreatedMessage message) {
        kafkaTemplate.send("hw30.order.created", message);
    }
}
