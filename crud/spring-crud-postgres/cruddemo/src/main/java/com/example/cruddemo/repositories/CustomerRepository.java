package com.example.cruddemo.repositories;

import com.example.cruddemo.entities.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    List<Customer> findByName(String name);
    
}
