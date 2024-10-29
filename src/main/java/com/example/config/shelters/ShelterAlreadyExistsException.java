package com.example.config.shelters;

public class ShelterAlreadyExistsException extends RuntimeException {
    public ShelterAlreadyExistsException(String message) {
        super(message);
    }
}

class ShelterNotFoundException extends RuntimeException {
    public ShelterNotFoundException(String message) {
        super(message);
    }
}