package com.example.testrest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CountryValidator implements ConstraintValidator<Country, String> {

    List<String> authors = Arrays.asList("USA", "Canada");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return authors.contains(value);

    }
}