package com.example.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonRepository repository;

  @Autowired
  public PersonController(PersonRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  public Person savePerson(@RequestBody Person person) {
    return repository.save(person);
  }

  @GetMapping
  public List<Person> findAll() {
    return repository.findAll();
  }

 
}