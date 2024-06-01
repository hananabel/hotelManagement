package com.example.hotelManagementSystem.controllers;

import com.example.hotelManagementSystem.apis.*;
import com.example.hotelManagementSystem.dtos.BookingDTO;
import com.example.hotelManagementSystem.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/booking")
public class BookingController {

    private final AddBookingApi addBookingApi;
    private final GetBookingsApi getBookingsApi;
    private final GetBookingByIdApi getBookingByIdApi;
    private final UpdateBookingApi updateBookingApi;
    private final DeleteBookingApi deleteBookingApi;

    @Autowired

    public BookingController(AddBookingApi addBookingApi, GetBookingsApi getBookingsApi, GetBookingByIdApi getBookingByIdApi, UpdateBookingApi updateBookingApi, DeleteBookingApi deleteBookingApi) {
        this.addBookingApi = addBookingApi;
        this.getBookingsApi = getBookingsApi;
        this.getBookingByIdApi = getBookingByIdApi;
        this.updateBookingApi = updateBookingApi;
        this.deleteBookingApi = deleteBookingApi;
    }

    @PostMapping("/addBooking")
    public ResponseEntity<ApiResponse<BookingDTO>> addBooking(@RequestBody BookingDTO bookingDTO) {
        return addBookingApi.addBooking(bookingDTO);
    }

    @GetMapping("/getAllBookings")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        return getBookingsApi.getAllBookings();
    }

    @GetMapping(path = "/getBooking/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        return getBookingByIdApi.getBookingById(id);
    }

    @PutMapping("/updateBooking/{id}")
    public ResponseEntity<ApiResponse<BookingDTO>> updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        return updateBookingApi.updateBooking(id, bookingDTO);
    }

    @DeleteMapping("/deleteBooking/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        return deleteBookingApi.deleteBooking(id);
    }
}
