package com.sample.ehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootEhcacheDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootEhcacheDemoApplication.class, args);
  }

}
