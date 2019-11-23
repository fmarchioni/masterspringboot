package com.masterspringboot.solrdemo;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;


@SolrDocument(solrCoreName = "customer_core")
public class CustomerDoc {

    @Id
    @Indexed
    private String id;

    @Indexed(name = "username", type = "string")
    private String username;

    @Indexed(name = "address", type = "string")
    private String address;

    @Indexed(name = "phone_number", type = "string")
    private String phoneNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }




}