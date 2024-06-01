package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DeleteCustomerApi {

    private final CustomerService customerService;

    @Autowired
    public DeleteCustomerApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    public ResponseEntity<String> deleteCustomer(Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully");
    }
}
