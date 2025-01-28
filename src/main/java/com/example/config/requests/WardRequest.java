package com.example.config.requests;

import jakarta.validation.constraints.*;

public class WardRequest {

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
    @NotBlank(message = "Animal name is required")
    private String animalName;
    @NotBlank(message = "Animal age is required")
    private String animalAge;
    @NotBlank(message = "Animal sex is required")
    private String animalSex;
    @NotBlank(message = "Animal size is required")
    private String animalSize;
    @NotBlank(message = "Shelter is required")
    private Long shelter;
//    @NotBlank(message = "User is required")
//    private Long user;

    public WardRequest(String firstName, String lastName, String email, String contactNumber, String experience, String typeOfAnimal, String animalName, String animalAge, String animalSex, String animalSize, Long shelter) {
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
        this.shelter = shelter;
   //     this.user=user;
    }

    public WardRequest() {

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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(String typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
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

    public Long getShelter() {
        return shelter;
    }

    public void setShelter(Long shelter) {
        this.shelter = shelter;
    }
    //    public Long getUser() {
//        return user;
//    }
//
//    public void setUser(Long user) {
//        this.user = user;
//    }
}
