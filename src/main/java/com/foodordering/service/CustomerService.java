
package com.foodordering.service;

import com.foodordering.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();
    private long nextId = 1;

    public Customer registerCustomer(Customer customer) {
        customer.setId(nextId++);
        customers.add(customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }
}
