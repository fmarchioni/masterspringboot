package com.example.samplewebapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonServiceTest {
	@Autowired
	private PersonService personService;

	@Test
	public void testSavePerson() {
		Person person = new Person("John", "Doe");
		Person savedPerson = personService.savePerson(person);
		assertThat(savedPerson).isNotNull();
		assertThat(savedPerson.getId()).isNotNull();
		assertThat(savedPerson.getName()).isEqualTo(person.getName());
		assertThat(savedPerson.getSurname()).isEqualTo(person.getSurname());
	}

	@Test
	public void testFindAll() {
		List<Person> persons = personService.findAll();
		assertThat(persons).isNotEmpty();
	}

	@Test
	public void testFindById() {
		Optional<Object> person = personService.findById(1L);
		assertThat(person).isPresent();
	}

	@Test
	public void testFindBySurname() {
		Optional<List<Person>> persons = personService.findBySurname("Doe");
		assertThat(persons).isPresent();
	}
}

