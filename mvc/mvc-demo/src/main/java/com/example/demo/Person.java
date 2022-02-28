package com.example.demo;
 

public class Person {
  private String name;
  private String surname;

  public Person(String name, String surname) {
    super();
    this.name = name;
    this.surname = surname;
  }

  public Person() {
    super();
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
}