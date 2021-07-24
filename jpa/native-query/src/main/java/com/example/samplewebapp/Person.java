package com.example.samplewebapp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.SQLUpdate;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
@Entity
@NamedNativeQueries({

    @NamedNativeQuery(
        name = "Person.findAllPersons",
        query =
            "SELECT * " +
            "FROM Person ", resultClass = Person.class
    ),

    @NamedNativeQuery(
        name = "Person.findPersonByName",
        query =
            "SELECT * " +
            "FROM Person p " +
            "WHERE p.name = ?", resultClass = Person.class)
    
})
public class Person {


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String surname;


    public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Person(String name, String surname) {
        super();
        this.name = name;
        this.surname = surname;
    }

    public Person() {
        super();
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}

}