package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.BookingDTO;
import com.example.hotelManagementSystem.response.ApiResponse;
import com.example.hotelManagementSystem.services.BookingService;
import com.example.hotelManagementSystem.validations.BookingValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateBookingApi {

    private final BookingService bookingService;
    private final BookingValidationUtils validate;

    @Autowired
    public UpdateBookingApi(BookingService bookingService, BookingValidationUtils validate) {
        this.bookingService = bookingService;
        this.validate = validate;
    }

    public ResponseEntity<ApiResponse<BookingDTO>> updateBooking(Long id, BookingDTO bookingDTO) {
        try {
            validate.validateUpdatedBooking(bookingDTO);
            BookingDTO bookingDTOUpdated = bookingService.updateBooking(id, bookingDTO);
            ApiResponse<BookingDTO> response = new ApiResponse<>("Booking updated", bookingDTOUpdated);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            // Return bad request with error message
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));
        }
    }
}
