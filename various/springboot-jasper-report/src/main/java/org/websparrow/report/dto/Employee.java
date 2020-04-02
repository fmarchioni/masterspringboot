package org.websparrow.report.dto;

public class Employee {

	private int id;
	private String name;
	private String oraganization;
	private String designation;
	private int salary;

	public Employee(int id, String name, String oraganization, String designation, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.oraganization = oraganization;
		this.designation = designation;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOraganization() {
		return oraganization;
	}

	public void setOraganization(String oraganization) {
		this.oraganization = oraganization;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}
