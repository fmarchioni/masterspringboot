package com.example.demolog4j2;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class CustomerRepository {
    List<Customer> customerList = new ArrayList<Customer>();
    private static final Logger logger = LogManager.getLogger(CustomerRepository.class);
    @PostConstruct
    public void init(){

        Customer c1 = new Customer(1, "frank");
        Customer c2 = new Customer(2, "john");

        customerList.add(c1);
        logger.debug("Added Customer : {}", () -> c1);
        customerList.add(c2);
        logger.debug("Added Customer : {}", () -> c2);
    }
    public List<Customer> getData() {
        return customerList;
    }
}
