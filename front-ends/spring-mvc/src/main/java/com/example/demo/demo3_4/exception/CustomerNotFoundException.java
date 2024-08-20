package com.example.demo.demo3_4.exception;

public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException() {
		super();
	}

	public CustomerNotFoundException(String customMessage) {
		super(customMessage);
	}
}
