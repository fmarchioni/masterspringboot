package com.example.testrest;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController
{
   @Autowired
   CustomerRepository repository;

    @RequestMapping(path= "/", method = RequestMethod.GET, produces = {"application/json"})
    public List<Customer> findAll()
    {
        return repository.getData();
    }

    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Customer> addCustomer(
            @RequestBody Customer customer)
            throws Exception
    {
        repository.save(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

}
