package com.example.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

  public void runRestClient() {
    RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
    List<User> list = restClient.get().uri("/users").retrieve()
    .body(new ParameterizedTypeReference<List<User>>() {
    });
    
    for (User u: list) {
      System.out.println("User id "+u.id());
      System.out.println("User id "+u.username());
      System.out.println("User id "+u.name());
      System.out.println("User id "+u.email());
      System.out.println("==================");
    }
  }

  @Bean
  public Runnable myRunnable() {
    return () -> runRestClient();
  }

  @Override
  public void run(String... args) throws Exception {
 
 
    myRunnable().run();
  }
}