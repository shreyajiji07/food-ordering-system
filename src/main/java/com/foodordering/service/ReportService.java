package com.foodordering.service;

import com.foodordering.model.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final OrderService orderService;

    public ReportService(OrderService orderService) {
        this.orderService = orderService;
    }

    public Map<String, Object> getSalesReport() {
        List<Order> completedOrders = orderService.getAllOrders()
                .stream()
                .filter(order -> "COMPLETED".equals(order.getStatus()))
                .toList();

        double totalSales = completedOrders.stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();

        Map<String, Object> report = new HashMap<>();
        report.put("completedOrders", completedOrders.size());
        report.put("totalSales", totalSales);

        return report;
    }
}