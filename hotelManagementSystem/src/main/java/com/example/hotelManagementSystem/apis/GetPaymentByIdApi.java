package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.PaymentDTO;
import com.example.hotelManagementSystem.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPaymentByIdApi {

    private final PaymentService paymentService;

    public PaymentDTO getPaymentById(Long id) {
        return paymentService.getPaymentById(id);
    }
}
