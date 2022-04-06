package com.example.shopbackend.repository;

import com.example.shopbackend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrderDetails_Id(Long orderId);
}
