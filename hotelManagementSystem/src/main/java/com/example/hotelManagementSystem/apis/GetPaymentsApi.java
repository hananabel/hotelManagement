package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.PaymentDTO;
import com.example.hotelManagementSystem.response.ApiResponse;
import com.example.hotelManagementSystem.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetPaymentsApi {

    private final PaymentService paymentService;

    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getAllPayments());
    }
}
