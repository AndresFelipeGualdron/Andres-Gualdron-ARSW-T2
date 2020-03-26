package edu.eci.arsw.coronavirusapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.coronavirus"})
public class CoronavirusAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoronavirusAPIApplication.class, args);
    }
}
