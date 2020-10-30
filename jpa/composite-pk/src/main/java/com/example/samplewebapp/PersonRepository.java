package com.example.samplewebapp;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, PersonId> {

    List<Person> findBySurname(String surname);
}