package com.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements CustomerRepository {
    private final JdbcTemplate jdbcTemplate;

    public CustomerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Customer>  rowMapper = (rs, rowNum) -> new Customer(rs.getString("name"),rs.getString("address"));

    @Override
    public List<Customer> findAll() {
        String findAllStates = """
                select * from Customers
                """;
        return jdbcTemplate.query(findAllStates, rowMapper);
    }

    @Override
    public String findByName(String name) {
        String findByName = """
                select address from Customers where name = ?;
                """;
        return jdbcTemplate.queryForObject(findByName, String.class, name);
    }
}
