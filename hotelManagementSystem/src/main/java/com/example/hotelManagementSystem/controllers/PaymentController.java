package com.example.hotelManagementSystem.controllers;

import com.example.hotelManagementSystem.apis.*;
import com.example.hotelManagementSystem.dtos.PaymentDTO;
import com.example.hotelManagementSystem.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final AddPaymentApi addPaymentApi;
    private final GetPaymentsApi getPaymentsApi;
    private final GetPaymentByIdApi getPaymentByIdApi;
    private final UpdatePaymentApi updatePaymentApi;
    private final DeletePaymentApi deletePaymentApi;

    @PostMapping("/addPayment")
    public ResponseEntity<ApiResponse<PaymentDTO>> addPayment(@RequestBody PaymentDTO paymentDTO) {
        return addPaymentApi.addPayment(paymentDTO);
    }

    @GetMapping("/getAllPayments")
    public ResponseEntity<List<PaymentDTO>> getPayments() {
        return getPaymentsApi.getAllPayments();
    }

    @GetMapping("/getPayment/{id}")
    public PaymentDTO getPaymentById(@PathVariable long id) {
        return getPaymentByIdApi.getPaymentById(id);
    }

    @PutMapping("/updatePayment/{id}")
    public ResponseEntity<ApiResponse<PaymentDTO>> updatePayment(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        return updatePaymentApi.updatePayment(id, paymentDTO);
    }

    @DeleteMapping("/deletePayment/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable long id) {
        return deletePaymentApi.deletePayment(id);
    }
}
