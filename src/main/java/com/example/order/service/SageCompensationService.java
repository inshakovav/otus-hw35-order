package com.example.order.service;

import com.example.order.dto.DeliveryRejectedMessage;
import com.example.order.dto.PaymentRejectedMessage;
import com.example.order.dto.WarehouseReservationRejectedMessage;
import com.example.order.entity.OrderEntity;
import com.example.order.entity.OrderStatus;
import com.example.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SageCompensationService {

    private final OrderRepository orderRepository;

    @Transactional
    public void executePaymentReject(PaymentRejectedMessage message) {
        OrderEntity order = findOrder(message.getOrderId());
        log.info("Payment was rejected: {}", message);
        order.setStatus(OrderStatus.REJECTED_BY_PAYMENT);
        orderRepository.save(order);
    }

    @Transactional
    public void executeWarehouseReject(WarehouseReservationRejectedMessage message) {
        OrderEntity order = findOrder(message.getOrderId());
        log.info("Warehouse reservation was rejected: {}", message);
        order.setStatus(OrderStatus.REJECTED_BY_WAREHOUSE);
        orderRepository.save(order);
    }

    @Transactional
    public void executeDeliveryReject(DeliveryRejectedMessage message) {
        OrderEntity order = findOrder(message.getOrderId());
        log.info("Delivery was rejected: {}", message);
        order.setStatus(OrderStatus.REJECTED_BY_DELIVERY);
        orderRepository.save(order);
    }

    private OrderEntity findOrder(long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NumberFormatException("Wrong payment rejection. Can't find order by payment order id" + orderId));
    }
}
