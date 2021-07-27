package com.example.samplewebapp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

 

 

public interface CustomerRepository extends CrudRepository<Customer, CustomerEmbeddable> {

    List<Customer> findByEmail(String email);
}
 
