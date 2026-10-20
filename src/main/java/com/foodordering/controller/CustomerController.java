
package com.foodordering.controller;

import com.foodordering.model.Customer;
import com.foodordering.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer registerCustomer(@RequestBody Customer customer) {
        if (customer.getName() == null ||
            customer.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (customer.getEmail() == null ||
            !customer.getEmail().matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Valid email is required");
        }

        return customerService.registerCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
