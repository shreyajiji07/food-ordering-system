package com.foodordering.controller;

import com.foodordering.model.Customer;
import com.foodordering.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable Long id) {

        Customer customer = customerService.getCustomerById(id);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(
            @RequestBody Customer customer) {

        if (customer.getName() == null
                || customer.getName().isBlank()
                || customer.getEmail() == null
                || customer.getEmail().isBlank()
                || customer.getPhone() == null
                || customer.getPhone().isBlank()) {

            return ResponseEntity.badRequest().build();
        }

        Customer savedCustomer = customerService.addCustomer(customer);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCustomer);
    }
}