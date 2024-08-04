package com.example.demo.demo2_2.controller;

import com.example.demo.demo2_2.model.Customer;
import com.example.demo.demo2_2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// http://localhost:8080/swagger-ui/index.html

@RestController
public class DemoController {
    @Autowired
    CustomerRepository repository;
    @GetMapping("/list")
    public Iterable<Customer> findAll() {
        return repository.findAll();
    }
    @GetMapping("/byId")
    public Optional<Customer> findOne(@RequestParam Long id) {
        return repository.findById(id);
    }

    // Find by name (inherited from JpaRepository)
    @GetMapping("/byName")
    public List<Customer> findByName(@RequestParam String name) {
        return repository.findByName(name);
    }

    // Find by name and surname
    @GetMapping("/byNameAndSurname")
    public List<Customer> findByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return repository.findByNameAndSurname(name, surname);
    }

    // Find by name containing a string
    @GetMapping("/byNameContaining")
    public List<Customer> findByNameContaining(@RequestParam String name) {
        return repository.findByNameContaining(name);
    }

    // Find by email address
    @GetMapping("/byEmail")
    public List<Customer> findByEmail(@RequestParam String email) {
        return repository.findByEmail(email);
    }

    // Find by email containing a string
    @GetMapping("/byEmailContaining")
    public List<Customer> findByEmailContaining(@RequestParam String email) {
        return repository.findByEmailContaining(email);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        repository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }


}
