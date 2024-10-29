package com.example.config.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ShelterRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone number is required")
    private String contactNumber;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;


    // Constructors, Getters, and Setters
    public ShelterRequest() {}

    public ShelterRequest(String name, String address, String contactNumber, String description, Integer capacity) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
