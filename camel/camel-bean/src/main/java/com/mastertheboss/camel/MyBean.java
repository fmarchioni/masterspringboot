package com.mastertheboss.camel;

public class MyBean {


    public void setTime(String s) {
       System.out.println(s+new java.util.Date());
    }

    public void done() {
        System.out.println("Done");
    }


}