package com.example.testrest;


import java.util.List;

public interface CustomerRepository {

    List<Customer> findAll();
    Customer findCustomer(int id);

}
