package com.example;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Operation {

    @Id
    private Long id;
    private Double amount;
    private String currency;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

  
    public Double getAmount() {
        return amount;
    }

    public void setAmount( Double amount ) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency( String currency ) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Operation [id=" + id + ", amount=" + amount + ", currency=" + currency + "]";
    }

    

   
}
