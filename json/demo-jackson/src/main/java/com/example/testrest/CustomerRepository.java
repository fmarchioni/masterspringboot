package com.example.testrest;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerRepository {
    List<Customer> customerList = new ArrayList<Customer>();

    @PostConstruct
    public void init(){
        try {
            customerList.add(new Customer(1, "frank",new SimpleDateFormat("dd/MM/yyyy").parse("17/10/1970")));
            customerList.add(new Customer(2, "john",new SimpleDateFormat("dd/MM/yyyy").parse("25/07/1980")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public List<Customer> getData() {
        return customerList;
    }
}
