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
        customerList.add(new Customer(1, "frank"));
        customerList.add(new Customer(2, "john"));
    }
    public List<Customer> getData() {
        return customerList;
    }
}
