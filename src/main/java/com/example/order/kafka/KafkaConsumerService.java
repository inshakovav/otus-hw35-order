package com.example.order.kafka;

import com.example.order.dto.DeliveryExecutedMessage;
import com.example.order.dto.DeliveryRejectedMessage;
import com.example.order.dto.PaymentRejectedMessage;
import com.example.order.dto.WarehouseReservationRejectedMessage;
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

    @KafkaListener(topics = "${order.kafka.payment-rejected-topic}", groupId = "${order.kafka.message-group-name}")
    public void receivePaymentRejected(PaymentRejectedMessage message) {
        try {
            sageCompensationService.executePaymentReject(message);
        } catch (Exception e) {
            log.warn("Kafka unknown error Order processing: ", message);
        }
    }

    @KafkaListener(topics = "${order.kafka.warehouse-rejected-topic}", groupId = "${order.kafka.message-group-name}")
    public void receiveWarehouseRejected(WarehouseReservationRejectedMessage message) {
        try {
            sageCompensationService.executeWarehouseReject(message);
        } catch (Exception e) {
            log.warn("Kafka unknown error Order processing: ", message);
        }
    }

    @KafkaListener(topics = "${order.kafka.delivery-executed-topic}", groupId = "${order.kafka.message-group-name}")
    public void receiveDeliveryExecuted(DeliveryExecutedMessage message) {
        try {
            sageCompensationService.executeDeliveryExecution(message);
        } catch (Exception e) {
            log.warn("Kafka unknown error Order processing: ", message);
        }
    }

    @KafkaListener(topics = "${order.kafka.delivery-rejected-topic}", groupId = "${order.kafka.message-group-name}")
    public void receiveDeliveryRejected(DeliveryRejectedMessage message) {
        try {
            sageCompensationService.executeDeliveryReject(message);
        } catch (Exception e) {
            log.warn("Kafka unknown error Order processing: ", message);
        }
    }
}
