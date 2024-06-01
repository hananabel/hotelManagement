package com.example.hotelManagementSystem.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer roomNo;
    private String roomType;
    private Integer capacity;
    private BigDecimal price;
    private Boolean availability;
}
