package com.example.model;

public class Customer {
  private int id;
  private String name;

  public Customer(int id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}