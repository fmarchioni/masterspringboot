package com.example.testrest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController
{
    @Autowired
    CustomerRepository repository;

    @GetMapping("/list")
    public List<Customer> findAll()
    {
        return repository.getData();
    }

    @PostMapping(path="/save",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody Customer customer)
    {
        System.out.println("got "+customer);
        repository.save(customer);
        System.out.println("Saved customer");
    }
}
