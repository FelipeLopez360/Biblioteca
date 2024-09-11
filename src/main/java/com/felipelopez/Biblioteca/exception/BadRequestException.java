package com.felipelopez.Biblioteca.exception;


public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
