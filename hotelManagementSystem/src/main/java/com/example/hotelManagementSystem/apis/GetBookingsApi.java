package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.BookingDTO;
import com.example.hotelManagementSystem.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetBookingsApi {

    private final BookingService bookingService;

    @Autowired
    public GetBookingsApi(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public ResponseEntity<List<BookingDTO>> getAllBookings() {

        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBookings());
    }


}
