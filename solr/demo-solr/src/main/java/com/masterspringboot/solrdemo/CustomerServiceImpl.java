package com.masterspringboot.solrdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    SolrRepository userSolrRepo;

    @Override
    public void storeUser(CustomerDoc doc) {

        userSolrRepo.save(doc);
    }

    @Override
    public List<CustomerDoc> getCustomers() {
        return userSolrRepo.getCustomers();
    }

    @Override
    public void deleteCustomer(String id) {
        userSolrRepo.deleteById(id);
    }
}