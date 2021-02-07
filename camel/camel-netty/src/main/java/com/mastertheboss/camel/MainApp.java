package com.mastertheboss.camel;

import org.apache.camel.main.Main;

public class MainApp {

    public static void main(String... args) throws Exception {
        // use Camels Main class
        Main main = new Main();
        main.addRouteBuilder(MyRouteBuilder.class);
        main.run(args);

    }

}

