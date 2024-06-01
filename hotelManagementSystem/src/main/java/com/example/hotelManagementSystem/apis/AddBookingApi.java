package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.BookingDTO;
import com.example.hotelManagementSystem.response.ApiResponse;
import com.example.hotelManagementSystem.services.BookingService;
import com.example.hotelManagementSystem.validations.BookingValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AddBookingApi {

    private final BookingService bookingService;
    private final BookingValidationUtils validate;

    @Autowired
    public AddBookingApi(BookingService bookingService, BookingValidationUtils validate) {
        this.bookingService = bookingService;
        this.validate = validate;
    }

    public ResponseEntity<ApiResponse<BookingDTO>> addBooking(BookingDTO bookingDTO) {
        try {
            validate.validateBooking(bookingDTO);
            BookingDTO newBooking = bookingService.addBooking(bookingDTO);
            ApiResponse<BookingDTO> response = new ApiResponse<>("Booking added", newBooking);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch(IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));
        }
    }
}
