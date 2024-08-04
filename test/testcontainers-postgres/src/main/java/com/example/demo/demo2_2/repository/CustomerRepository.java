package com.example.demo.demo2_2.repository;

import com.example.demo.demo2_2.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);

    // Find by name and surname (like in SQL: WHERE name = ? AND surname = ?)
    List<Customer> findByNameAndSurname(String name, String surname);

    // Find by name containing a specific string (like in SQL: WHERE name LIKE ?)
    List<Customer> findByNameContaining(String name);

    // Find by email address (like in SQL: WHERE email = ?)
    List<Customer> findByEmail(String email);

    // Find by email address containing a specific string (like in SQL: WHERE email LIKE ?)
    List<Customer> findByEmailContaining(String email);
}