package org.example.cardservice.application;

import java.math.BigDecimal;
import java.time.LocalDate;


public class CardApplicationDto {

	public User user;
	public BigDecimal cardCapacity;

	public static class User {
		public String name;
		public String surname;
		public String idNo;
		public LocalDate dateOfBirth;
	}
}
