package com.example.mongodbdemo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class CustomerController {
	@Autowired
	private CustomerRepository repository;

	@PostMapping("/add") 
	public String saveCustomer(@RequestBody Customer customer){
		repository.save(customer);

		return "Added Successfully";
	}

	@GetMapping("/list")
	public List<Customer> getCustomers() {

		return repository.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable int id){
		repository.deleteById(id);

		return "Deleted Successfully";
	}
}
