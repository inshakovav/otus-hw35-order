package com.example.order.service;

import com.example.order.dto.PaymentRejectedMessage;
import com.example.order.entity.OrderEntity;
import com.example.order.entity.OrderStatus;
import com.example.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ErrorCompensationService {

    private final OrderRepository orderRepository;

    @Transactional
    public void executePaymentReject(PaymentRejectedMessage message) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(message.getOrderId());
        if(optionalOrder.isEmpty()) {
            log.warn("Wrong payment rejection. Can't find order by payment order id: {}", message);
        }

        log.info("Payment was rejected: {}", message);
        OrderEntity order = optionalOrder.get();
        order.setStatus(OrderStatus.REJECTED_BY_PAYMENT);
        orderRepository.save(order);
    }
}
