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

    }
    public List<Customer> getData() {
        return customerList;
    }

    public void save(Customer c) {
        customerList.add(c);
    }
}
