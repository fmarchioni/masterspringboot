package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class PersonServiceTest {

	@Autowired
	private PersonService personService;

	@BeforeEach
	public void setup(){
		personService.createPersonTable();
	}
	@Test
	public void testInsert() {
		Person person = new Person(1L,"John","Doe");
		personService.insert(person);

		Person personResult = personService.findById(1L);
		assertThat(personResult).isEqualToComparingFieldByField(person);

		Person person2 = new Person(2L,"Gabriel","Fox");
		personService.insert(person2);
		assertThat(personService.findAll()).hasSize(2);
	}

	@Test
	public void testUpdate() {
		Person person = new Person(1L,"Steve","Smith");
		personService.update(person);

		Person personResult = personService.findById(1L);
		assertThat(personResult).isNotNull();
	}

	@Test
	public void testDelete() {
		Person person = new Person(1L,"John","Doe");
		personService.insert(person);
		personService.delete(1L);
		assertThat(personService.findAll()).isEmpty();
	}
}
