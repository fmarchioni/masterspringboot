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
	public CommandLineRunner demo(PersonRepository repository) {
		return (args) -> {
			// save a couple of persons
			repository.save(new Person("Jack","Smith"));
			repository.save(new Person("Joe","Black"));
			repository.save(new Person("Martin","McFly"));


			// fetch all persons
			log.info("Persons found with findAll():");
			log.info("-------------------------------");
			for (Person person : repository.findAllPersons()) {
				log.info(person.toString());
			}
			log.info("");

	 

			// fetch persons by last name
			log.info("Person found with findByName('Jack'):");
			log.info("--------------------------------------------");
			repository.findPersonByName("Jack").forEach(smith -> {
				log.info(smith.toString());
			});

			log.info("");
		};
	}
}