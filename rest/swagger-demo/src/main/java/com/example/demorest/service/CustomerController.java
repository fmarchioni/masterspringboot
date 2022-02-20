package com.example.demorest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
public class CustomerController
{
   @Autowired
   CustomerRepository repository;

   @Operation(summary = "List all customers")
   @ApiResponses(value = { 
     @ApiResponse(responseCode = "200", description = "Found the following customers", 
       content = { @Content(mediaType = "application/json", 
         schema = @Schema(implementation = Customer.class)) }),
     @ApiResponse(responseCode = "500", description = "Internal server error", 
       content = @Content)}) 
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
