package com.example.camel.jdbc;



import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultRegistry;
import org.apache.commons.dbcp.*;
import javax.sql.DataSource;


public class MainApp {


    public static void main(String... args) throws Exception {
        // use Camels Main class

        String url = "jdbc:postgresql://localhost:5432/cameldb";
        DataSource dataSource = setupDataSource(url);

        DefaultRegistry reg = new DefaultRegistry();
        reg.bind("myDataSource", dataSource);

        CamelContext context = new DefaultCamelContext(reg);
        context.addRoutes(new MainApp().new MyRouteBuilder());
        context.start();
        Thread.sleep(5000);
        context.stop();


    }
    private static DataSource setupDataSource(String connectURI) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUsername("camel");
        ds.setPassword("camel");
        ds.setUrl(connectURI);
        return ds;
    }

     class MyRouteBuilder extends RouteBuilder {

        public void configure() {
            from("timer://foo?period=1000").process(new SimpleProcessor()).to(
                    "jdbc:myDataSource").to("log:output");

        }
    }
}