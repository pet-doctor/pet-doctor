package com.petdoctor;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PetDoctorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetDoctorApplication.class, args);
    }

}
