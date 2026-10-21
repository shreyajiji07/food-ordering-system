package com.foodordering.service;

import com.foodordering.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerById(Long id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Customer addCustomer(Customer customer) {
        long nextId = customers.stream()
                .mapToLong(Customer::getId)
                .max()
                .orElse(0) + 1;

        customer.setId(nextId);
        customers.add(customer);

        return customer;
    }
}