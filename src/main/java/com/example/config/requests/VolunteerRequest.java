package com.example.config.requests;

import javax.validation.constraints.NotBlank;


public class VolunteerRequest {
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Contact number is required")
    private String contactNumber;
    @NotBlank(message = "Shelter is required")
    private Long shelter;

    public VolunteerRequest(String firstName, String lastName, String email, String contactNumber, Long shelter) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.shelter = shelter;
    }

    public VolunteerRequest() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Long getShelter() {
        return shelter;
    }

    public void setShelter(Long shelter) {
        this.shelter = shelter;
    }
}
