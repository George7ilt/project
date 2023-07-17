package com.example.restapp.util.exceptions;

public class MasterNotFoundException extends RuntimeException {
    public MasterNotFoundException(String message) {
        super(message);
    }
}
