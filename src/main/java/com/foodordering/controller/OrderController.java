package com.foodordering.controller;

import com.foodordering.model.Order;
import com.foodordering.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable Long id) {

        Order order = orderService.getOrderById(id);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody Order request) {
                System.out.println("Customer ID: " + request.getCustomerId());
                System.out.println("Food ID: " + request.getFoodId());
                System.out.println("Quantity: " + request.getQuantity());

        if (request.getCustomerId() == null
                || request.getFoodId() == null
                || request.getQuantity() <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Order order = orderService.createOrder(
                request.getCustomerId(),
                request.getFoodId(),
                request.getQuantity()
        );

        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}