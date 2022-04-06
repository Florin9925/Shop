package com.example.shopbackend.repository;

import com.example.shopbackend.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findAllByUser_IdIs(Long userId);

    Optional<OrderDetails> findOrderDetailsByUser_IdAndId(Long userId, Long orderId);
}
