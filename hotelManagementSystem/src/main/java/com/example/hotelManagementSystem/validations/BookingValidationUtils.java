package com.example.hotelManagementSystem.validations;

import com.example.hotelManagementSystem.dtos.BookingDTO;
import com.example.hotelManagementSystem.entities.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingValidationUtils {

    public void validateBooking(BookingDTO booking) {
        if(booking == null) {
            throw new IllegalArgumentException("Enter all details");
        }
        if(booking.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer id is required");
        }
        if(booking.getRoomNo() == null) {
            throw new IllegalArgumentException("Room number is required");
        }
        if(booking.getCheckIn() == null) {
            throw new IllegalArgumentException("CheckIn is required");
        }
        if(booking.getCheckOut() == null) {
            throw new IllegalArgumentException("CheckOut is required");
        }
    }

    public void validateUpdatedBooking(BookingDTO booking) {
        if(booking.getCustomerId() != null) {
            throw new IllegalArgumentException("Customer id cannot be updated");
        }
        if(booking.getRoomNo() != null) {
            throw new IllegalArgumentException("Room number cannot be updated");
        }
    }
}
