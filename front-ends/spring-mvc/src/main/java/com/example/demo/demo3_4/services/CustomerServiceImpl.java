package com.example.demo.demo3_4.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.demo3_4.model.Customer;
import com.example.demo.demo3_4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.demo3_4.exception.CustomerNotFoundException;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository repo;

	@Override
	public Customer save(Customer customer) {
		return repo.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		return repo.findAll();
	}

	@Override
	public Customer getCustomerById(Long id) {
		Optional<Customer> opt = repo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new CustomerNotFoundException("Invoice with Id : " + id + " Not Found");
		}
	}

	@Override
	public void deleteCustomerById(Long id) {
		repo.delete(getCustomerById(id));
	}

	@Override
	public void update(Customer customer) {
		repo.save(customer);
	}
}