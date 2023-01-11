package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class PersonService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createPersonTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS person(id SERIAL, name VARCHAR(255), surname VARCHAR(255))");
    }

    public void insert(Person person) {
        jdbcTemplate.update("INSERT INTO person(id, name, surname) VALUES(?,?,?)", person.getId(), person.getName(), person.getSurname());
    }

    public void update(Person person) {
        jdbcTemplate.update("UPDATE person SET name = ?, surname = ? WHERE id = ?", person.getName(), person.getSurname(), person.getId());
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

    public Person findById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id = ?", new Object[] { id }, (rs, rowNum) -> {
            Person person = new Person();
            person.setId(rs.getLong("id"));
            person.setName(rs.getString("name"));
            person.setSurname(rs.getString("surname"));
            return person;
        });
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM person", (rs, rowNum) -> {
            Person person = new Person();
            person.setId(rs.getLong("id"));
            person.setName(rs.getString("name"));
            person.setSurname(rs.getString("surname"));
            return person;
        });
    }
}
