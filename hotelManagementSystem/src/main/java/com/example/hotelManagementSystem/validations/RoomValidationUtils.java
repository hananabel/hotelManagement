package com.example.hotelManagementSystem.validations;

import com.example.hotelManagementSystem.entities.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomValidationUtils {

    public void validateRoom(Room room) {
        if(room == null) {
            throw new IllegalArgumentException("Enter all details");
        }
        if(room.getRoomNo() == null) {
            throw new IllegalArgumentException("Room number cannot be null");
        }
        if(room.getCapacity() == null) {
            throw new IllegalArgumentException("Capacity cannot be null");
        }
        if(room.getCapacity() < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        if(room.getRoomType().isEmpty() || room.getRoomType() == null) {
            throw new IllegalArgumentException("Room type cannot be empty");
        }
        if(room.getPrice() == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if(room.getAvailability() == null) {
            throw new IllegalArgumentException("Availability cannot be null");
        }

    }

    public void validateUpdatedRoom(Room room) {
        if(room.getRoomNo() != null) {
            throw new IllegalArgumentException("Room number cannot be changed");
        }
        if(room.getRoomType() != null && room.getRoomType().isEmpty()) {
            throw new IllegalArgumentException("Room type cannot be empty");
        }
        if(room.getCapacity() != null && room.getCapacity() < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
    }
}
