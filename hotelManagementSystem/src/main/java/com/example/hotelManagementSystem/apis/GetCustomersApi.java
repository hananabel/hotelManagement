package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.CustomerDTO;
import com.example.hotelManagementSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetCustomersApi {

    private final CustomerService customerService;

    @Autowired
    public GetCustomersApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
    }
}
