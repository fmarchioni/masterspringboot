package com.sample.csv;

import java.io.Serializable;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", generateHeaderColumns = true)
public class User implements Serializable {
  @Override
  public String toString() {
    return "User [name=" + name + ", surname=" + surname + ", id=" + id + "]";
  }

  @DataField(pos = 1)
  private String name;

  @DataField(pos = 2)
  private String surname;

  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

}