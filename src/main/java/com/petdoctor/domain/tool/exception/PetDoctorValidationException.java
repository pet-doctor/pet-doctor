package com.petdoctor.domain.tool.exception;

public class PetDoctorValidationException extends RuntimeException {

    public PetDoctorValidationException(String message) {
        super("Validation exception occurred by: " + message);
    }
}
