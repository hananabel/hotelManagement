package com.example.hotelManagementSystem.dtos;

import com.example.hotelManagementSystem.entities.Customer;
import com.example.hotelManagementSystem.entities.PaymentStatus;
import com.example.hotelManagementSystem.entities.Room;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDTO {

    private Long customerId;
    private Integer roomNo;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    @Enumerated
    private PaymentStatus paymentStatus;

}
