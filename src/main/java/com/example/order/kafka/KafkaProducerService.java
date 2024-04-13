package com.example.order.kafka;

import com.example.order.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("my-topic", message);
    }

    public void sendMessage2(String message) {
        kafkaTemplate.send("my-topic2", message);
    }

    public void sendOrder(OrderEntity message) {
        kafkaTemplate.send("my-topic", message);
    }
}
