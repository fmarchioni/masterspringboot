package com.example.testrest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Validated
@RestController
public class CustomerController
{
   @Autowired
   CustomerRepository repository;


   /*
    public void setCustomerRepository(CustomerRepository repository){
        this.repository = repository;
    }
    */
    @RequestMapping("/list")
    public List<Customer> findAll()
    {
        return repository.getData();
    }

    @RequestMapping("/find/{id}")
    public Customer findOne(@PathVariable @Min(1) @Max(3) int id)
    {
        return repository.getData().get(id);

    }

    @PostMapping("/save")
    public Customer newCustomer(@Valid @RequestBody Customer customer) {
        return repository.save(customer);
    }
}
