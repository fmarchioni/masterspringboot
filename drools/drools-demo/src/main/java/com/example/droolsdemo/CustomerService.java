package com.example.droolsdemo;

import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;



@Service
public class CustomerService {

    private KieSession kieSession=new DroolsConfiguration().getKieSession();

    public Customer insertCustomer(Customer customer){
        kieSession.insert(customer);
        kieSession.fireAllRules();
        return  customer;

    }

}
