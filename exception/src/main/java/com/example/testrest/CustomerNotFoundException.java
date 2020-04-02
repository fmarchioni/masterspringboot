package com.example.testrest;


public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(int id) {
        super("Customer id not found : " + id);
    }

}