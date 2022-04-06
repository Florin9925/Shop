package com.example.shopbackend.service.order;

import com.example.shopbackend.model.OrderDetails;

import java.util.List;

public interface OrderService {

    List<OrderDetails> findAll();

    List<OrderDetails> findAllById(Long userId);

    OrderDetails save(OrderDetails order);

    OrderDetails findOrderByUserIdWhereOrderId(Long userId, Long OrderId);

    void deleteById(Long id);

    void deleteAll();
}
