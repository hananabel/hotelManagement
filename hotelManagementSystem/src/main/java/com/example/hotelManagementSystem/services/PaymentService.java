package com.example.hotelManagementSystem.services;

import com.example.hotelManagementSystem.dtos.BookingDTO;
import com.example.hotelManagementSystem.dtos.PaymentDTO;
import com.example.hotelManagementSystem.entities.Booking;
import com.example.hotelManagementSystem.entities.Payment;
import com.example.hotelManagementSystem.entities.PaymentStatus;
import com.example.hotelManagementSystem.exceptions.AmountNotCorrect;
import com.example.hotelManagementSystem.exceptions.BookingNotFoundException;
import com.example.hotelManagementSystem.exceptions.PaymentNotFoundException;
import com.example.hotelManagementSystem.repositories.BookingRepository;
import com.example.hotelManagementSystem.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    public PaymentDTO addPayment(PaymentDTO paymentDTO) {
        Optional<Booking> bookingOptional = bookingRepository.findById(paymentDTO.getBookingId());
        if (bookingOptional.isEmpty()) {
            throw new BookingNotFoundException("Booking with id " + paymentDTO.getBookingId() + " not found");
        }
        Payment payment = new Payment();
        payment.setBooking(bookingOptional.get());
        payment.setAmount(paymentDTO.getAmount());
        payment.setDate(paymentDTO.getDate());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentType(paymentDTO.getPaymentType());
        Booking booking = bookingOptional.get();
        LocalDate checkInDate = booking.getCheckIn().toLocalDate();
        LocalDate checkOutDate = booking.getCheckOut().toLocalDate();
        BigDecimal roomPrice = booking.getRoom().getPrice();
        long daysBetween = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        BigDecimal totalPrice = roomPrice.multiply(new BigDecimal(daysBetween));
        if(paymentDTO.getAmount().equals(totalPrice)) {
            booking.setPaymentStatus(PaymentStatus.PAID);
        }
        else {
            throw new AmountNotCorrect("Amount required to pay is " + totalPrice);
        }

        paymentRepository.save(payment);
        return paymentDTO;
    }

    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public PaymentDTO getPaymentById(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isEmpty()) {
            throw new PaymentNotFoundException("Payment with id" + id + " is not found");
        }
        return modelMapper.map(paymentOptional.get(), PaymentDTO.class);
    }

    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isEmpty()) {
            throw new PaymentNotFoundException("Payment with id" + id + " is not found");

        }
        Payment payment = paymentOptional.get();
        if(paymentDTO.getPaymentType() != null) {
            payment.setPaymentType(paymentDTO.getPaymentType());
        }
        if(paymentDTO.getAmount() != null) {
            payment.setAmount(paymentDTO.getAmount());
        }
        if(paymentDTO.getDate() != null) {
            payment.setDate(paymentDTO.getDate());
        }


        return modelMapper.map(paymentRepository.save(payment), PaymentDTO.class);
    }

    public void deletePayment(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isEmpty()) {
            throw new PaymentNotFoundException("Payment with id" + id + " is not found");
        }
        paymentRepository.delete(paymentOptional.get());
    }


}
