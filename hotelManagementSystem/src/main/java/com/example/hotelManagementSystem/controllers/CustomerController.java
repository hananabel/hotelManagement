package com.example.hotelManagementSystem.controllers;

import com.example.hotelManagementSystem.apis.*;
import com.example.hotelManagementSystem.dtos.CustomerDTO;
import com.example.hotelManagementSystem.entities.Customer;
import com.example.hotelManagementSystem.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/customer")
public class CustomerController {

    private final AddCustomerApi addCustomerApi;
    private final GetCustomersApi getCustomersApi;
    private final UpdateCustomerApi updateCustomerApi;
    private final DeleteCustomerApi deleteCustomerApi;
    private final GetCustomerByIdApi getCustomerByIdApi;

    @Autowired

    public CustomerController(AddCustomerApi addCustomerApi, GetCustomersApi getCustomersApi, UpdateCustomerApi updateCustomerApi, DeleteCustomerApi deleteCustomerApi, GetCustomerByIdApi getCustomerByIdApi) {
        this.addCustomerApi = addCustomerApi;
        this.getCustomersApi = getCustomersApi;
        this.updateCustomerApi = updateCustomerApi;
        this.deleteCustomerApi = deleteCustomerApi;
        this.getCustomerByIdApi = getCustomerByIdApi;
    }

   // @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'USER')")
    @PostMapping("/addCustomer")
    public ResponseEntity<ApiResponse<CustomerDTO>> addCustomer(@RequestBody Customer customer) {
        return addCustomerApi.addCustomer(customer);
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        return getCustomersApi.getCustomers();
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'USER')")
    @GetMapping( "/getCustomer/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        return getCustomerByIdApi.getCustomerById(id);
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<ApiResponse<CustomerDTO>> updateCustomer(@PathVariable Long id,@RequestBody Customer customer) {
        return updateCustomerApi.updateCustomer(id, customer);
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        return deleteCustomerApi.deleteCustomer(id);
    }
}
