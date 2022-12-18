package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    private List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping(value = "/customer")
    private String getCustomer(@RequestParam(required = false, name = "name", defaultValue = "Maharashtra") String name) {
        return customerService.findByName(name);
    }
}
