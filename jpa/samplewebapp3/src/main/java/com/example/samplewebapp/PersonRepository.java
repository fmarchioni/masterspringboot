package com.example.samplewebapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findBySurname(String surname);
}
