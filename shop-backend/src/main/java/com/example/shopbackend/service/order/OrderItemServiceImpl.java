package com.example.shopbackend.service.order;

import com.example.shopbackend.model.OrderItem;
import com.example.shopbackend.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService{


    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findAllByOrderId(Long orderId) {
        return orderItemRepository.findAllByOrderDetails_Id(orderId);
    }
}
