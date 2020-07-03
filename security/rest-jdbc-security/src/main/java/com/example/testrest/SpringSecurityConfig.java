package com.example.testrest;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableAutoConfiguration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }


}
