package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletePaymentApi {

    private final PaymentService paymentService;

    public ResponseEntity<String> deletePayment(Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok("Deleted Payment Successfully");
    }
}
