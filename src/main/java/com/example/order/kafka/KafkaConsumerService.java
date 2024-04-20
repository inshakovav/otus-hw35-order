package com.example.order.kafka;

import com.example.order.dto.PaymentRejectedMessage;
import com.example.order.entity.OrderEntity;
import com.example.order.entity.OrderStatus;
import com.example.order.repository.OrderRepository;
import com.example.order.service.ErrorCompensationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final ErrorCompensationService errorCompensationService;

    @KafkaListener(topics = "${order.kafka.payment-rejected-topic}", groupId = "${order.kafka.message-group-name}")
    public void receivePaymentRejected(PaymentRejectedMessage message) {
        errorCompensationService.executePaymentReject(message);
    }
}
