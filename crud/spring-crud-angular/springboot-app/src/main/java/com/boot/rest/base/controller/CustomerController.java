package com.boot.rest.base.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.boot.rest.base.model.Customer;
import com.boot.rest.base.service.CustomerService;

@RestController
@RequestMapping(value = "customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Customer registerCustomer(@RequestBody Customer customerVO) {

		return this.customerService.insert(customerVO);
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<Customer> findAll() {

		return this.customerService.findAll();
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Customer findById(@PathVariable int id) {
		return this.customerService.findById(id);
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customerVO) {

		return this.customerService.updateCustomer(id, customerVO);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable int id) {

		this.customerService.delete(id);
	}
}
