package com.example.order.repository;

import com.example.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findFirstByOrderByIdDesc();

    Optional<OrderEntity> findFirstByAccountIdAndPriceAndBookingAt(Long accountId, BigDecimal price, Timestamp bookingAt);
}
