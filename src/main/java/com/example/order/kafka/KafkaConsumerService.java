package com.example.order.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumerService {
    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void receiveMessage(String message) {
        // Process the received message
        log.info("Received message: {} from topic: {}", message, "my-topic");
    }

    @KafkaListener(topics = "my-topic2", groupId = "my-group")
    public void receiveMessage2(String message) {
        // Process the received message
        log.info("Received message: {} from topic: {}", message, "my-topic2");
    }
}
