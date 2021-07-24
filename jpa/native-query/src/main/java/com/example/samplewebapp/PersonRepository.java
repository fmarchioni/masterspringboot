package com.example.samplewebapp;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

	 List<Person> findAllPersons();
	 
	 List<Person> findPersonByName(String name);
	 
}