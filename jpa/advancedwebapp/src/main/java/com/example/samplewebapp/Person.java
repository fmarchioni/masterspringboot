package com.example.samplewebapp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.SQLUpdate;

@Entity
@SQLInsert(sql = "INSERT INTO {h-schema}Person (name, id) VALUES (?, ?)")
@SQLDelete(sql = "DELETE FROM {h-schema}Person WHERE id = ?", check = ResultCheckStyle.COUNT)
@SQLUpdate(sql = "UPDATE {h-schema}Person SET name = ? WHERE id = ? ")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;


    public Person(String name) {
        super();
        this.name = name;
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




}