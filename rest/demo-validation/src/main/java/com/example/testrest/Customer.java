package com.example.testrest;

import javax.validation.constraints.Size;

public class Customer {

    private int id;
    @Size(min = 5, message = "Enter at least 5 Characters.")
    private String name;

    @Country
    private String country;

    public Customer(int id, String name, String country) {
        super();
        this.id = id;
        this.name = name;
        this.country = country;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
