package com.sample;

import org.springframework.stereotype.Component;


@Component("myBean")
public class MySpringBean {

        public String saySomething(String s) {
           return s.toUpperCase();
    }

}
