package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.BookingDTO;
import com.example.hotelManagementSystem.dtos.PaymentDTO;
import com.example.hotelManagementSystem.response.ApiResponse;
import com.example.hotelManagementSystem.services.PaymentService;
import com.example.hotelManagementSystem.validations.PaymentValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdatePaymentApi {

    private final PaymentService paymentService;
    private final PaymentValidationUtils validate;

    public ResponseEntity<ApiResponse<PaymentDTO>> updatePayment(Long id, PaymentDTO paymentDTO) {

        try {
            validate.validateUpdatedPayment(paymentDTO);
            PaymentDTO paymentDTOUpdated = paymentService.updatePayment(id, paymentDTO);
            ApiResponse<PaymentDTO> response = new ApiResponse<>("Payment updated", paymentDTOUpdated);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            // Return bad request with error message
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));
        }
    }
}
