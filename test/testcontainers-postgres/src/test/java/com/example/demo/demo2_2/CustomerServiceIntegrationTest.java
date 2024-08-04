package com.example.demo.demo2_2;


import com.example.demo.demo2_2.model.Customer;
import com.example.demo.demo2_2.repository.CustomerRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceIntegrationTest {



    @Autowired
    private CustomerRepository customerRepository;

    @LocalServerPort
    private Integer port;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    void testFindAll() {
        Customer customer1 = new Customer("John", "Doe", "john.doe@example.com");
        Customer customer2 = new Customer("Jane", "Doe", "jane.doe@example.com");
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        List<Customer> list = customerRepository.findAll();
        assertNotNull(list, "The list of customers should not be null");
        assertEquals(2, list.size(), "The list should contain exactly 2 customers");

        assertTrue(list.stream().anyMatch(customer -> "John".equals(customer.getName())),
                "The list should contain a customer with the name 'John'");
        assertTrue(list.stream().anyMatch(customer -> "Jane".equals(customer.getName())),
                "The list should contain a customer with the name 'Jane'");

    }
/*
    @Test
    void testFindById() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");
        customerRepository.save(customer);

        CustomerDTO result = customerService.findById(customer.getId());

        assertNotNull(result);
        assertEquals(customer.getName(), result.customerName());
    }

    @Test
    void testFindByIdNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            customerService.findById(999L);
        });
    }

    @Test
    void testCreate() {
        CustomerDTO customerDTO = new CustomerDTO(0, "John", "Doe", "john.doe@example.com");

        CustomerDTO result = customerService.create(customerDTO);

        assertNotNull(result);
        assertEquals(customerDTO.customerName(), result.customerName());
    }

    @Test
    void testUpdate() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");
        customerRepository.save(customer);
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), "John", "Smith", "john.smith@example.com");

        CustomerDTO result = customerService.update(customerDTO, customer.getId());

        assertNotNull(result);
        assertEquals(customerDTO.surname(), result.surname());
    }

    @Test
    void testUpdateNotFound() {
        CustomerDTO customerDTO = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com");

        assertThrows(ResponseStatusException.class, () -> {
            customerService.update(customerDTO, 999L);
        });
    }

    @Test
    void testDelete() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");
        customerRepository.save(customer);

        customerService.delete(customer.getId());

        assertFalse(customerRepository.findById(customer.getId()).isPresent());
    }

 */
}
