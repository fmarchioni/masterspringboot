package com.masterspringboot.config;

import com.masterspringboot.service.MyBannerService;
import com.masterspringboot.service.MyBannerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(MyBannerService.class)
@EnableConfigurationProperties(MyBannerProperties.class)
public class MyBannerAutoConfiguration {
    @Autowired
    private MyBannerProperties greeterProperties;

    //conditional bean creation
    @Bean
    @ConditionalOnMissingBean
    public MyBannerService helloService(){

        return new MyBannerServiceImpl(greeterProperties);
    }
}