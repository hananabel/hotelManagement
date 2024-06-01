package com.example.hotelManagementSystem.exceptions;

public class RoomAlreadyExists extends RuntimeException{

    public RoomAlreadyExists(String message) {
        super(message);
    }
}
