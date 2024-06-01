package com.example.hotelManagementSystem.dtos;

import lombok.Data;

@Data
public class CustomerDTO {
    private String name;
    private String email;
    private String phone;
    private Integer age;
    private String gender;
}
