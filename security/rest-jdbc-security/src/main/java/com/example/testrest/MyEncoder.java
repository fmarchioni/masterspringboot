package com.example.testrest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode("password1");
        System.out.println(encoded);
        encoded = encoder.encode("password2");
        System.out.println(encoded);
    }
}