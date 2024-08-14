package com.boot.rest.base.service;

import java.util.List;

import com.boot.rest.base.model.Customer;

public interface CustomerService {

	public Customer insert(Customer customerVO);

	public List<Customer> findAll();

	public void delete(int id);

	public Customer findById(int id);

	public Customer updateCustomer(int id, Customer customerVO);
}
