package com.example.samplewebapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PersonController {
	@PersistenceContext
	EntityManager em;
    

    @RequestMapping("/findNames")
    public List<Person> findAll() {
        
        Query q = em.createNamedQuery("Person.findAllPersons");      
        return q.getResultList();
        
    }

    @RequestMapping("/findByName/{name}")
    public List<Person> findOne(@PathVariable String name) {
        Query q = em.createNamedQuery("Person.findPersonByName");      
        q.setParameter(1, name);
        return q.getResultList();

    }
}