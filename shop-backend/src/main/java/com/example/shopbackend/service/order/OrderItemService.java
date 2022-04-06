package com.example.shopbackend.service.order;

import com.example.shopbackend.model.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> findAllByOrderId(Long orderId);
}
