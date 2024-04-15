package com.example.order;

import com.example.order.dto.OrderCreatedDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
@Getter
public class KafkaConsumer {

    private CountDownLatch latch = new CountDownLatch(1);
    private String payload;
    private OrderCreatedDto receivedMessage;

    @KafkaListener(topics = "my-topic2", groupId = "my-group")
    public void receive(OrderCreatedDto message) {
        log.info("received payload='{}'", message.toString());
//        payload = message.toString();
        receivedMessage = message;
        latch.countDown();
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }
}
