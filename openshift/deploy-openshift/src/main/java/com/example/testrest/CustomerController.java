package com.example.testrest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

    @RequestMapping("/one/{id}")
    public Customer findOne(@PathVariable int id)
    {
        return repository.getData().get(id);

    }
}
