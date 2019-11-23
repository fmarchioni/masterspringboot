package com.masterspringboot.solrdemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value ="getCustomers", method = RequestMethod.GET)
    @ResponseBody
    public List<CustomerDoc> getCustomers() {
        return customerService.getCustomers();
    }

    @RequestMapping(value ="createCustomer", method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public String createCustomer(@RequestBody CustomerDoc doc) {
        customerService.storeUser(doc);
        return "Added Customer!";
    }
    @RequestMapping(value ="deleteCustomer", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCustomer(@RequestParam String id) {
        customerService.deleteCustomer(id);
    }
}
