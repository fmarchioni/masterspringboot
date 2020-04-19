package com.example.testrest;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.*;

import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
@Configuration
public class MyConfiguration {

    @Bean
    public HttpMessageConverters customConverters() {
        MarshallingHttpMessageConverter xmlConverter =new MarshallingHttpMessageConverter();
        XStreamMarshaller xstream = new XStreamMarshaller();
        xmlConverter.setMarshaller(xstream);
        xmlConverter.setUnmarshaller(xstream);

        return new HttpMessageConverters(xmlConverter);
    }

}