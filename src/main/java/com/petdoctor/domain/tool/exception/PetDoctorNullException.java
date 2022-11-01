package com.petdoctor.domain.tool.exception;

public class PetDoctorNullException extends RuntimeException{

    public PetDoctorNullException(String message) {
        super("Null pointer exception occurred by: " + message);
    }
}
