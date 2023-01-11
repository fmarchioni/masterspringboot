package com.example;

public class Person {
    Long id;
    String name;
    String surname;

    public Person(Long id, String name, String surname) {
        super();
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Person() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", surname=" + surname + "]";
    }
}