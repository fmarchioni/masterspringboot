package com.example.testrest;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MockCustomerRepository implements CustomerRepository {

    private final List<Customer> customers = new ArrayList<>();

    public MockCustomerRepository() {
        this.customers.add(new Customer(1, "John", "Smith"));
        this.customers.add(new Customer(2, "Mark", "Spencer"));
        this.customers.add(new Customer(3, "Andy", "Doyle"));
    }

    @Override
    public List<Customer> findAll() {
        return this.customers;
    }

    @Override
    public Customer findCustomer(int id) {
        for (Customer customer : this.customers) {
            if (ObjectUtils.nullSafeEquals(customer.getId(), id)) {
                return customer;
            }
        }
        return null;
    }

}