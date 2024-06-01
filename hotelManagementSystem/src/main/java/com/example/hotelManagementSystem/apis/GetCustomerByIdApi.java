package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.CustomerDTO;
import com.example.hotelManagementSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GetCustomerByIdApi {

    private final CustomerService customerService;

    @Autowired
    public GetCustomerByIdApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    public ResponseEntity<CustomerDTO> getCustomerById(Long id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        return ResponseEntity.ok(customerDTO);
    }
}
