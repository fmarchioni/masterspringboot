package com.example.samplewebapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner demoIdClass(PersonRepository repository) {
		return (args) -> {
			// save a couple of persons
			repository.save(new Person("Jack", "Smith","address","jack@email.com"));
			repository.save(new Person("Joe", "Black","address","joe@email.com"));

			// fetch all persons
			log.info("Persons found with findAll():");
			log.info("-------------------------------");
			for (Person person : repository.findAll()) {
				log.info(person.toString());
			}
			log.info("");

			// fetch an individual person by ID
			repository.findById(new PersonId("Jack","Smith"))
					.ifPresent(person -> {
						log.info("Person found with findById:");
						log.info("--------------------------------");
						log.info(person.toString());
						log.info("");
					});

			// fetch persons by last name
			log.info("Person found with findBySurname('Black'):");
			log.info("--------------------------------------------");
			repository.findBySurname("Black").forEach(smith -> {
				log.info(smith.toString());
			});

			log.info("");
		};
	}
	
	@Bean
	public CommandLineRunner demoEmbeddedId(CustomerRepository repository) {
		return (args) -> {
			// save a couple of persons
			repository.save(new Customer(new CustomerEmbeddable("Jack", "Smith"),"address","jack@email.com"));
			repository.save(new Customer(new CustomerEmbeddable("Joe", "Black"),"address","joe@email.com"));

			// fetch all persons
			log.info("Persons found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual person by ID
			repository.findById(new CustomerEmbeddable("Jack","Smith"))
					.ifPresent(person -> {
						log.info("Customers found with findById:");
						log.info("--------------------------------");
						log.info(person.toString());
						log.info("");
					});

			// fetch persons by last name
			log.info("Customers found with email('joe@email.com'):");
			log.info("--------------------------------------------");
			repository.findByEmail("joe@email.com").forEach(smith -> {
				log.info(smith.toString());
			});

			log.info("");
		};
	}
}