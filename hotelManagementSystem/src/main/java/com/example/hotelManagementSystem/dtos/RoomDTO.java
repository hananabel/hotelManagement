package com.example.hotelManagementSystem.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomDTO {

    private Integer roomNo;
    private String roomType;
    private Integer capacity;
    private BigDecimal price;
    private Boolean availability = true;
}
