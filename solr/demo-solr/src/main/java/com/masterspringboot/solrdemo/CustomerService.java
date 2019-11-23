package com.masterspringboot.solrdemo;

import java.util.List;


public interface CustomerService {

    void storeUser(CustomerDoc doc);
    List<CustomerDoc> getCustomers();
    void deleteCustomer(String id);
}