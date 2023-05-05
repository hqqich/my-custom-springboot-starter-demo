package org.myboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = "org.myboot.controller")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
