package com.example.hotelManagementSystem.dtos;

import com.example.hotelManagementSystem.entities.Booking;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentDTO {

    private Long id;
    private String paymentType;
    private BigDecimal amount;
    private LocalDate date;
    private Long bookingId;
}
