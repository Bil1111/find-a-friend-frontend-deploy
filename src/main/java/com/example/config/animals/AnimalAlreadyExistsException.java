package com.example.config.animals;

public class AnimalAlreadyExistsException extends RuntimeException {
    public AnimalAlreadyExistsException(String message) {
        super(message);
    }
}

class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}