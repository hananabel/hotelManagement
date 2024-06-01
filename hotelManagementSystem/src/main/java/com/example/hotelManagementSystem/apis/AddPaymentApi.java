package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.PaymentDTO;
import com.example.hotelManagementSystem.dtos.RoomDTO;
import com.example.hotelManagementSystem.response.ApiResponse;
import com.example.hotelManagementSystem.services.PaymentService;
import com.example.hotelManagementSystem.validations.PaymentValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
public class AddPaymentApi {

    private final PaymentService paymentService;
    private final PaymentValidationUtils validate;

    public ResponseEntity<ApiResponse<PaymentDTO>> addPayment(PaymentDTO paymentDTO) {

        try {
            validate.validatePayment(paymentDTO);
            PaymentDTO newPayment = paymentService.addPayment(paymentDTO);
            ApiResponse<PaymentDTO> response = new ApiResponse<>("Payment added successfully", newPayment);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch(IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));
        }

    }
}
