package com.petdoctor.domain.tool.exception;

public class PetDoctorNotFoundException extends RuntimeException {
    public PetDoctorNotFoundException(String message) {
        super("Entity not found occurred by: " + message);
    }
}
