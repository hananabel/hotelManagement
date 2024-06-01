package com.example.hotelManagementSystem.exceptions;


public class CustomerAlreadyExistsException extends RuntimeException{

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
