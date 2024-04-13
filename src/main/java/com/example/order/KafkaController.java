package com.example.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    private final KafkaProducerService producerService;

    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/messages")
    public void sendMessageToKafka(@RequestBody String message) {

        producerService.sendMessage(message);
    }

    @PostMapping("/messages2")
    public void sendMessageToKafka2(@RequestBody String message) {

        producerService.sendMessage2(message);
    }
}
