package com.example.testrest;

import org.springframework.data.jpa.repository.JpaRepository;

 
import java.util.ArrayList;
import java.util.List;
 
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> customerList = new ArrayList<Customer>();
 
}
