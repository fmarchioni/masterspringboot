package com.example.samplewebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService   {
    @Autowired
    private PersonRepository personRepository;


    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Object> findById(long l) {
        return Optional.of(personRepository.findById(l));
    }

    public Optional<List<Person>> findBySurname(String surname) {
        return Optional.of(personRepository.findBySurname(surname));
    }
}
