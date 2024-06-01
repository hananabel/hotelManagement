package com.example.hotelManagementSystem.services;

import com.example.hotelManagementSystem.dtos.CustomerDTO;
import com.example.hotelManagementSystem.entities.Customer;
import com.example.hotelManagementSystem.exceptions.CustomerAlreadyExistsException;
import com.example.hotelManagementSystem.exceptions.CustomerNotFoundException;
import com.example.hotelManagementSystem.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public CustomerDTO addCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(customer.getEmail());
        if(customerOptional.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with the same email already exists");
        }
        customerRepository.save(customer);
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;

    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());

    }

    public CustomerDTO getCustomerById(long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isEmpty()) {
            throw new CustomerNotFoundException("Customer with id " + id + " does not exist");
        }
        return modelMapper.map(customerOptional.get(), CustomerDTO.class);
    }

    public CustomerDTO updateCustomer(long id, Customer updatedCustomer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isEmpty()) {
            throw new CustomerNotFoundException("Customer with id " + id + " does not exist");
        }
        Customer customer = customerOptional.get();
        if(updatedCustomer.getName() != null) {
            customer.setName(updatedCustomer.getName());
        }

        if(updatedCustomer.getEmail() != null) {
            Optional<Customer> customerEmail = customerRepository.findByEmail(updatedCustomer.getEmail());
            if(customerEmail.isPresent()) {
                throw new CustomerAlreadyExistsException("Customer with the same email already exists");
            }
            customer.setEmail(updatedCustomer.getEmail());

        }

        if(updatedCustomer.getPhone() != null) {
            Optional<Customer> customerPhone = customerRepository.findByPhone(updatedCustomer.getPhone());
            if(customerPhone.isPresent()) {
                throw new CustomerAlreadyExistsException("Customer with phone no. " + updatedCustomer.getPhone() + " already exists");
            }
            customer.setPhone(updatedCustomer.getPhone());
        }

        if(updatedCustomer.getGender() != null) {
            customer.setGender(updatedCustomer.getGender());
        }

        if(updatedCustomer.getAge() != null) {
            customer.setAge(updatedCustomer.getAge());
        }
        customerRepository.save(customer);
        return modelMapper.map(customer, CustomerDTO.class);

    }

    public void deleteCustomer(long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isEmpty()) {
            throw new CustomerNotFoundException("Customer with id " + id + " does not exist");
        }
        customerRepository.deleteById(id);
    }
}
