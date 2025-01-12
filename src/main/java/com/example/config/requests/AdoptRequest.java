package com.example.config.requests;

import com.example.config.shelters.Shelter;
import jakarta.persistence.Column;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class AdoptRequest {
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Contact number is required")
    private String contactNumber;
    @NotBlank(message = "Experience is required")
    private String experience;
    @NotBlank(message = "typeOfAnimal is required")
    private String typeOfAnimal;
    @NotBlank(message = "animalName is required")
    private String animalName;
    @NotBlank(message = "animalAge is required")
    private String animalAge;
    @NotBlank(message = "animalSex is required")
    private String animalSex;
    @NotBlank(message = "animalSize is required")
    private String animalSize;

//    @NotBlank(message = "Shelter is required")
//    private Long shelter;


    public AdoptRequest(String firstName, String lastName, String email, String contactNumber, String experience, String typeOfAnimal, String animalName, String animalAge, String animalSex, String animalSize) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.experience = experience;
        this.typeOfAnimal = typeOfAnimal;
        this.animalName = animalName;
        this.animalAge = animalAge;
        this.animalSex = animalSex;
        this.animalSize = animalSize;
    }

    public AdoptRequest() {

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

//    public Long getShelter() {
//        return shelter;
//    }
//
//    public void setShelter(Long shelter) {
//        this.shelter = shelter;
//    }

    public String getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(String typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(String animalAge) {
        this.animalAge = animalAge;
    }

    public String getAnimalSex() {
        return animalSex;
    }

    public void setAnimalSex(String animalSex) {
        this.animalSex = animalSex;
    }

    public String getAnimalSize() {
        return animalSize;
    }

    public void setAnimalSize(String animalSize) {
        this.animalSize = animalSize;
    }
}
