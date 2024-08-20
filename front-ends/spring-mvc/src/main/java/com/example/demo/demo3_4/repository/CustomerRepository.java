package com.example.demo.demo3_4.repository;

import com.example.demo.demo3_4.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
