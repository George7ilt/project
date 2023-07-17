package com.example.restapp.util.exceptions;

public class StudioNotFoundException extends RuntimeException{
    public StudioNotFoundException(String message) {
        super(message);
    }
}
