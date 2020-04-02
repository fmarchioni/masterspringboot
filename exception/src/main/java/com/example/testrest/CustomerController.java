package com.example.testrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CustomerController
{
   @Autowired
   CustomerRepository repository;



    @RequestMapping("/list")
    public List<Customer> findAll()
    {

        return repository.findAll();

    }

    @RequestMapping("/find/{id}")
    public Customer findOne(@PathVariable int id)
    {
        Customer c =  repository.findCustomer(id);

        if (c != null)
            return c;
        else
            throw new CustomerNotFoundException(id);

    }
}
