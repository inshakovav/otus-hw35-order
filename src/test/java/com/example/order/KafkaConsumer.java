package com.example.order;

import com.example.order.dto.OrderCreatedMessage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
@Getter
public class KafkaConsumer {

    private CountDownLatch latch = new CountDownLatch(1);
    private String payload;
    private OrderCreatedMessage receivedMessage;

    @KafkaListener(topics = "${order.kafka.order-created-topic}", groupId = "${order.kafka.message-group-name}")
    public void receive(OrderCreatedMessage message) {
        log.info("received payload='{}'", message.toString());
//        payload = message.toString();
        receivedMessage = message;
        latch.countDown();
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }
}
