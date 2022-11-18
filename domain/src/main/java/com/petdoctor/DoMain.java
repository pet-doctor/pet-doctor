package com.petdoctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.petdoctor.data.repository")
public class DoMain {
    public static void main(String[] args) {
        SpringApplication.run(DoMain.class, args);
    }
}

