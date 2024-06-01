package com.example.hotelManagementSystem.validations;

import com.example.hotelManagementSystem.dtos.PaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidationUtils {

    public void validatePayment (PaymentDTO paymentDTO) {
        if(paymentDTO == null) {
            throw new IllegalArgumentException("Enter all details");
        }
        if (paymentDTO.getPaymentType() == null || paymentDTO.getPaymentType().equals("")) {

            throw new IllegalArgumentException("Payment type cannot be empty");
        }
        if (paymentDTO.getAmount() == null || paymentDTO.getAmount().equals("")) {
            throw new IllegalArgumentException("Amount cannot be empty");
        }
        if(paymentDTO.getDate() == null || paymentDTO.getDate().equals("")) {
            throw new IllegalArgumentException("Date cannot be empty");
        }
        if (paymentDTO.getBookingId() == null || paymentDTO.getBookingId().equals("")) {
            throw new IllegalArgumentException("BookingId cannot be empty");
        }
    }

    public void validateUpdatedPayment(PaymentDTO paymentDTO) {
        if(paymentDTO.getPaymentType() != null && paymentDTO.getPaymentType().equals("")) {
            throw new IllegalArgumentException("Payment type cannot be empty");
        }
        if(paymentDTO.getBookingId()!= null){
            throw new IllegalArgumentException("Booking id cannot be updated");
        }
        if(paymentDTO.getAmount() != null && paymentDTO.getAmount().equals("")) {
            throw new IllegalArgumentException("Amount cannot be empty");
        }
        if(paymentDTO.getDate() != null && paymentDTO.getDate().equals("")) {
            throw new IllegalArgumentException("Date cannot be empty");
        }
    }
}
