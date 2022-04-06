package com.example.shopbackend.controller;

import com.example.shopbackend.exception.InvalidDataException;
import com.example.shopbackend.model.OrderDetails;
import com.example.shopbackend.model.OrderItem;
import com.example.shopbackend.model.User;
import com.example.shopbackend.service.order.OrderItemService;
import com.example.shopbackend.service.order.OrderService;
import com.example.shopbackend.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Api(value = "Orders Controller", tags = "/orders")
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {

    private final UserService userService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<OrderDetails>> getAllOrders() {
        log.info("getAllOrders");

        List<OrderDetails> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}/items")
    @PreAuthorize("hasAuthority('order:read')")
    public ResponseEntity<List<OrderItem>> getAllOrderItems(@PathVariable Long orderId, Principal principal) {
        log.info("getAllOrderItems");

        User user = userService.findByUsername(principal.getName());
        List<OrderDetails> orders = orderService.findAllById(user.getId());

        var hasWantedOrder = orders.stream().anyMatch(order -> order.getId().equals(orderId));

        if (!hasWantedOrder) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<OrderItem> orderItems = orderItemService.findAllByOrderId(orderId);

        return ResponseEntity.ok(orderItems);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('order:read')")
    public ResponseEntity<List<OrderDetails>> getAllOrdersByUserId(Principal principal) {
        log.info("getAllOrdersByUserId");

        User user = userService.findByUsername(principal.getName());

        System.out.println("The current user " + user.getUsername());
        List<OrderDetails> orders = orderService.findAllById(user.getId());

        return ResponseEntity.ok(orders);
    }

    @Validated
    @PostMapping()
    @PreAuthorize("hasAuthority('order:write')")
    @Transactional
    public ResponseEntity<OrderDetails> createOrder(
            @RequestBody() @ApiParam(value = "OrderDetails", required = true) OrderDetails orderDetails,
            BindingResult bindingResult,
            Principal principal) {

        log.info("createOrder");


        if (bindingResult.hasErrors()) {
            throw new InvalidDataException("Invalid Order");
        }

        if (orderDetails.getUser() == null) {
            User user = userService.findByUsername(principal.getName());
            orderDetails.setUser(user);
        }

        OrderDetails newOrderDetails = orderService.save(orderDetails);

        return ResponseEntity.ok(newOrderDetails);
    }

    @PatchMapping("/{orderId}")
    @PreAuthorize("hasAuthority('order:write')")
    public ResponseEntity<OrderDetails> addOrderItemToOrder(
            @PathVariable Long orderId, @RequestBody @ApiParam Map<String, Object> requestBody) {

        log.info("addOrderItemToOrder");

        Objects.requireNonNull(orderId);
        final ObjectMapper objectReader = new ObjectMapper();
        requestBody.put("id", orderId);
        final OrderDetails orderDetails = objectReader.convertValue(requestBody, OrderDetails.class);
        return ResponseEntity.ok(orderService.save(orderDetails));
    }
}
