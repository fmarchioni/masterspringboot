package com.boot.rest.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.rest.base.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
}
