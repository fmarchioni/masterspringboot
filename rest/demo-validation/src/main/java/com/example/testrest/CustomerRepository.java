package com.example.testrest;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerRepository {
    List<Customer> customerList = new ArrayList<Customer>();

    @PostConstruct
    public void init(){
        customerList.add(new Customer(1, "frank","USA"));
        customerList.add(new Customer(2, "john","USA"));
    }
    public List<Customer> getData() {
        return customerList;
    }

    public Customer save(Customer c) {
        customerList.add(c);
        return c;
    }
}
