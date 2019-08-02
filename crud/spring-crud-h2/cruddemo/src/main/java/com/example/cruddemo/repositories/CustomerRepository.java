package com.example.cruddemo.repositories;

import com.example.cruddemo.entities.Customer;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    
    List<Customer> findByName(String name);
    
}
