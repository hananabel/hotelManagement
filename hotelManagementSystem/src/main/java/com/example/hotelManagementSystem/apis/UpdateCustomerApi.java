package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.CustomerDTO;
import com.example.hotelManagementSystem.entities.Customer;
import com.example.hotelManagementSystem.response.ApiResponse;
import com.example.hotelManagementSystem.services.CustomerService;
import com.example.hotelManagementSystem.validations.CustomerValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateCustomerApi {

    private final CustomerService customerService;
    private final CustomerValidationUtils validate;

    @Autowired
    public UpdateCustomerApi(CustomerService customerService, CustomerValidationUtils validate) {
        this.customerService = customerService;
        this.validate = validate;
    }

    public ResponseEntity<ApiResponse<CustomerDTO>> updateCustomer(Long id, Customer updatedCustomer) {
        try{
            validate.validateUpdatedCustomer(updatedCustomer);
            CustomerDTO customerDTO = customerService.updateCustomer(id, updatedCustomer);
            ApiResponse<CustomerDTO> response = new ApiResponse<>("Customer Updated successfully", customerDTO);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            // Return bad request with error message
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));
        }
    }


}
