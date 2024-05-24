package com.example.order.kafka;

import com.example.order.dto.PaymentExecutedMessage;
import com.example.order.dto.PaymentRejectedMessage;
import com.example.order.service.SageCompensationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final SageCompensationService sageCompensationService;

    @KafkaListener(topics = "${app.kafka.payment-rejected-topic}", groupId = "${app.kafka.message-group-name}")
    public void receivePaymentRejected(PaymentRejectedMessage message) {
        try {
            sageCompensationService.executePaymentReject(message);
        } catch (Exception e) {
            log.warn("Kafka unknown error Order processing: ", message);
        }
    }

    @KafkaListener(topics = "${app.kafka.payment-executed-topic}", groupId = "${app.kafka.message-group-name}")
    public void receivePaymentExecuted(PaymentExecutedMessage message) {
        try {
            sageCompensationService.executePaymentExecuted(message);
        } catch (Exception e) {
            log.warn("Kafka unknown error Order processing: ", message);
        }
    }
}
