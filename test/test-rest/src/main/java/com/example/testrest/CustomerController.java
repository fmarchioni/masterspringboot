package com.example.testrest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @RequestMapping("/list")
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @RequestMapping("/one/{id}")
    public Optional<Customer> findOne(@PathVariable Long id) {
        return repository.findById(id);

    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        repository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
}
