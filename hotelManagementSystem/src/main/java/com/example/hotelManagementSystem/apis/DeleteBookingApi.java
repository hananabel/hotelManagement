package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DeleteBookingApi {

    private final BookingService bookingService;

    @Autowired
    public DeleteBookingApi(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public ResponseEntity<String> deleteBooking(Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.status(HttpStatus.OK).body("Booking cancelled successfully");
    }
}
