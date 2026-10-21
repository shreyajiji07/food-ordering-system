package com.foodordering.controller;

import com.foodordering.model.Order;
import com.foodordering.service.OrderService;
import com.foodordering.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderStatusController {

    private final OrderService orderService;
    private final ReportService reportService;

    public OrderStatusController(OrderService orderService,
                                 ReportService reportService) {
        this.orderService = orderService;
        this.reportService = reportService;
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {

        String status = request.get("status");

        if (status == null ||
                !(status.equals("PLACED")
                || status.equals("PREPARING")
                || status.equals("COMPLETED"))) {
            return ResponseEntity.badRequest().build();
        }

        Order order = orderService.getOrderById(id);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        order.setStatus(status);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/reports/sales")
    public Map<String, Object> getSalesReport() {
        return reportService.getSalesReport();
    }
}