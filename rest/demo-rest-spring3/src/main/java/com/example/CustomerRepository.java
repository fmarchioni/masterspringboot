package com.example;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();

    String findByName(String name);
}
