package com.petdoctor.domain.tool.exception;

public class VetClinicNullException extends RuntimeException{

    public VetClinicNullException(String message) {
        super("Null pointer exception occurred by: " + message);
    }
}
