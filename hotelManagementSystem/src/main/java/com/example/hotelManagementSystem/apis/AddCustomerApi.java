package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.CustomerDTO;
import com.example.hotelManagementSystem.entities.Customer;
import com.example.hotelManagementSystem.response.ApiResponse;
import com.example.hotelManagementSystem.services.CustomerService;
import com.example.hotelManagementSystem.validations.CustomerValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AddCustomerApi {

    private final CustomerService customerService;
    private final CustomerValidationUtils validate;

    @Autowired
    public AddCustomerApi(CustomerService customerService, CustomerValidationUtils validate) {
        this.customerService = customerService;
        this.validate = validate;
    }

    public ResponseEntity<ApiResponse<CustomerDTO>> addCustomer(Customer customer) {
        try{
            validate.validateCustomer(customer);
            CustomerDTO customerDTO =  customerService.addCustomer(customer);
            ApiResponse<CustomerDTO> response = new ApiResponse<>("Customer added successfully",customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch(IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));
        }
    }
}
