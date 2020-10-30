package com.example.samplewebapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PersonController {

    @Autowired
    PersonRepository repository;

    @RequestMapping("/list")
    public List<Person> findAll() {

        List<Person> list = new ArrayList<Person>();
        repository.findAll().iterator().forEachRemaining(list::add);
        return list;

    }

    @RequestMapping("/one/{name}/{surname}")
    public Optional<Person> findById(@PathVariable String name,@PathVariable String surname) {
        return repository.findById(new PersonId(name,surname));

    }
}