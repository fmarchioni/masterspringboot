package com.example.demo.demo3_4.services;

import java.util.List;

import com.example.demo.demo3_4.model.Customer;

public interface ICustomerService {
	public Customer save(Customer customer);
    public List<Customer> findAll();
    public Customer getCustomerById(Long id);
    public void deleteCustomerById(Long id);
    public void update(Customer customer);
}
