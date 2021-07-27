package com.example.samplewebapp;


import javax.persistence.*;

@Entity
public class Customer {
	
	@EmbeddedId
	private CustomerEmbeddable customerPK;
 
    String address;
    String email;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public Customer(CustomerEmbeddable customerPK, String address, String email) {
        this.customerPK = customerPK;
        this.address=address;
        this.email=email;
    }

    public Customer() {
        super();
    }

	@Override
	public String toString() {
		return "Customer [customerPK=" + customerPK + ", address=" + address + ", email=" + email + "]";
	}


   
}