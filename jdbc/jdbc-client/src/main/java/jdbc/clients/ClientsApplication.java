package jdbc.clients;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ClientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientsApplication.class, args);
    }

    @Bean
    JdbcClient jdbcClient(DataSource dataSource) {
        return JdbcClient.create(dataSource);
    }

    @Bean
    ApplicationRunner runner(JdbcClient jdbcClient) {
        return args -> {
            RowMapper<Customer> planetRowMapper = (rs, rowNum) -> new Customer(rs.getString("name"), rs.getString("address"));

            List<String> list1 = new ArrayList<>(Arrays.asList("John Smith", "5th Avenue New york"));


            var insert = jdbcClient
                    .sql("insert into Customers (name,address ) values(?,?)")
                    .params(list1)
                    .update( );
           
            jdbcClient.sql("select * from Customers" )
                    .query(planetRowMapper)
                    .list()
                    .forEach(System.out::println);
        };
    }
 
}

record Customer(String name, String address) {
}

