package com.example.hotelManagementSystem.repositories;

import com.example.hotelManagementSystem.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
