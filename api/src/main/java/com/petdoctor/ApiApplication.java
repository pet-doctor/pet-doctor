package com.petdoctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ApiApplication.class, args);
        } catch (Exception e) {
            System.out.println("lol");
        }
    }
}
