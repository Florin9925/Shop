package com.example.shopbackend.service.order;

import com.example.shopbackend.exception.DataNotFoundException;
import com.example.shopbackend.exception.InvalidDataException;
import com.example.shopbackend.model.OrderDetails;
import com.example.shopbackend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDetails> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderDetails> findAllById(Long userId) {
        return orderRepository.findAllByUser_IdIs(userId);
    }

    @Override
    public OrderDetails save(OrderDetails order) {
        try {
            if (order.getCreatedAt() == null) {
                order.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            }
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new InvalidDataException("Invalid order!");
        }
    }

    @Override
    public OrderDetails findOrderByUserIdWhereOrderId(Long userId, Long orderId) {
        try {
            return orderRepository.findOrderDetailsByUser_IdAndId(userId, orderId).orElseThrow(
                    () -> new DataNotFoundException(
                            MessageFormat.format("Could not find order with order ID: {0} of user with ID: {1}", orderId, userId))
            );
        } catch (DataNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidDataException("Invalid order!");
        }
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }
}
