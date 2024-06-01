package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.BookingDTO;
import com.example.hotelManagementSystem.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GetBookingByIdApi {

    private final BookingService bookingService;

    @Autowired
    public GetBookingByIdApi(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public ResponseEntity<BookingDTO> getBookingById(Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }
}
