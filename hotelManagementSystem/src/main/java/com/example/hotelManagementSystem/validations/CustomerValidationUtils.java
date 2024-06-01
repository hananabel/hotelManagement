package com.example.hotelManagementSystem.validations;

import com.example.hotelManagementSystem.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidationUtils {

    public void validateCustomer(Customer customer) {
        if(customer == null) {
            throw new IllegalArgumentException("Enter all details");
        }
        if(customer.getName().isEmpty() || customer.getName() == null) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(customer.getEmail().isEmpty() || customer.getEmail() == null) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!customer.getEmail().matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if(customer.getPhone().isEmpty() || customer.getPhone() == null) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if(customer.getGender() == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }
        if(!(customer.getGender().equalsIgnoreCase("male") || customer.getGender().equalsIgnoreCase("female"))) {
            throw new IllegalArgumentException("Invalid gender");
        }
        if(customer.getAge() == null) {
            throw new IllegalArgumentException("Age cannot be empty");
        }
        if(customer.getAge() < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }

    public void validateUpdatedCustomer(Customer customer) {
        if (customer.getName() != null && customer.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (customer.getEmail() != null) {
            if(customer.getEmail().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be empty");
            }
            else {
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                if (!customer.getEmail().matches(emailRegex)) {
                    throw new IllegalArgumentException("Invalid email format");
                }
            }
        }
        if(customer.getPhone() != null && customer.getPhone().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if(customer.getGender() != null && !(customer.getGender().equalsIgnoreCase("male") || customer.getGender().equalsIgnoreCase("female"))) {
            throw new IllegalArgumentException("Invalid gender");
        }
        if(customer.getAge() != null && (customer.getAge() < 0)) {
            throw new IllegalArgumentException("Age cannot be negative");
        }

    }
}
