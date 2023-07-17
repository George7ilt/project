package com.example.restapp.util.exceptions;

public class TattooNotFoundException extends RuntimeException {
    public TattooNotFoundException(String message) {
        super(message);
    }
}
