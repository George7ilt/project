package com.example.restapp.util;

import com.example.restapp.util.exceptions.ClientNotFoundException;
import com.example.restapp.util.exceptions.MasterNotFoundException;
import com.example.restapp.util.exceptions.StudioNotFoundException;
import com.example.restapp.util.exceptions.TattooNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionControllerHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse onClientNotFoundException(ClientNotFoundException e) {
        return new ErrorResponse(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MasterNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse onMasterNotFoundException(MasterNotFoundException e) {
        return new ErrorResponse(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(StudioNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse onStudioNotFoundException(StudioNotFoundException e) {
        return new ErrorResponse(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(TattooNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse onTattooNotFoundException(TattooNotFoundException e) {
        return new ErrorResponse(e.getMessage(), LocalDateTime.now());
    }
}
