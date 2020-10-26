package com.spring_data.spring_data_intro_lab.exception;

public class IllegalRegistrationException extends RuntimeException{
    public IllegalRegistrationException(String message) {
        super(message);
    }
}
