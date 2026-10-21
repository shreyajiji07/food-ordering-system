package com.foodordering.service;

import com.foodordering.model.Customer;
import com.foodordering.model.FoodItem;
import com.foodordering.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    private final CustomerService customerService;
    private final FoodService foodService;

    public OrderService(CustomerService customerService,
                        FoodService foodService) {
        this.customerService = customerService;
        this.foodService = foodService;
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(Long id) {
        return orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Order createOrder(Long customerId, Long foodId, int quantity) {
        Customer customer = customerService.getCustomerById(customerId);
        FoodItem food = foodService.getFoodById(foodId);

        if (customer == null || food == null
                || !food.isAvailable() || quantity <= 0) {
            return null;
        }

        long nextId = orders.stream()
                .mapToLong(Order::getId)
                .max()
                .orElse(0) + 1;

        double unitPrice = food.getPrice();
        double totalAmount = unitPrice * quantity;

        Order order = new Order(
                nextId,
                customerId,
                foodId,
                food.getName(),
                quantity,
                unitPrice,
                totalAmount,
                "PLACED"
        );

        orders.add(order);
        return order;
    }
}