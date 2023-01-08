package com.example.samplewebapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(PersonService personService) {
		return (args) -> {
			// save a couple of persons
			personService.savePerson(new Person("Jack", "Smith"));
			personService.savePerson(new Person("Joe", "Black"));
			personService.savePerson(new Person("Martin", "Bauer"));


			// fetch all persons
			log.info("Persons found with findAll():");
			log.info("-------------------------------");
			for (Person person : personService.findAll()) {
				log.info(person.toString());
			}
			log.info("");

			// fetch an individual person by ID
			personService.findById(1L)
					.ifPresent(person -> {
						log.info("Person found with findById(1L):");
						log.info("--------------------------------");
						log.info(person.toString());
						log.info("");
					});


			log.info("Person found with findBySurname('Black'):");
			log.info("--------------------------------------------");

			Optional<List<Person>> people = personService.findBySurname("Smith");
			if (people.isPresent()) {
				people.get().forEach(person -> {
					log.info(people.toString());
				});
			}


			log.info("");
		};
	}
}