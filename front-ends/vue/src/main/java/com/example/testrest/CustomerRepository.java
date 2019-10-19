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
        customerList.add(new Customer(1, "Fred","Flinstone"));
        customerList.add(new Customer(2, "Barney","Rubble"));
        customerList.add(new Customer(2, "Wilma","Flinstone"));
        customerList.add(new Customer(2, "Bettey","Rubble"));
        customerList.add(new Customer(2, "Bamm","Bamm"));
    }
    public List<Customer> getData() {
        return customerList;
    }
}
