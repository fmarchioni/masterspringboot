package com.example.droolsdemo;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CustomerService {

    private final KieContainer kieContainer;

    @Autowired
    public CustomerService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Server addServerFacts(Server product) {

        KieSession kieSession = kieContainer.newKieSession("rulesSession");
        kieSession.insert(product);
        kieSession.fireAllRules();
        kieSession.dispose();
        return product;
    }


}
