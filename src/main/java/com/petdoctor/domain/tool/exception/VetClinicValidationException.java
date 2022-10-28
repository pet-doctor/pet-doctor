package com.petdoctor.domain.tool.exception;

public class VetClinicValidationException extends RuntimeException {

    public VetClinicValidationException(String message) {
        super("Validation exception occurred by: " + message);
    }
}
