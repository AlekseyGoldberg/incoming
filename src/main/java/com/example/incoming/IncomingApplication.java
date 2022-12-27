package com.example.incoming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class IncomingApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncomingApplication.class, args);
    }

}
