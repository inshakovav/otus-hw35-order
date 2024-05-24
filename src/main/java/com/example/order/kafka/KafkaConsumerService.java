//package com.example.order.kafka;
//
//import com.example.order.dto.PaymentRejectedMessage;
//import com.example.order.service.SageCompensationService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class KafkaConsumerService {
//
//    private final SageCompensationService sageCompensationService;
//
//    @KafkaListener(topics = "${order.kafka.payment-rejected-topic}", groupId = "${order.kafka.message-group-name}")
//    public void receivePaymentRejected(PaymentRejectedMessage message) {
//        try {
//            sageCompensationService.executePaymentReject(message);
//        } catch (Exception e) {
//            log.warn("Kafka unknown error Order processing: ", message);
//        }
//    }
//
//}
