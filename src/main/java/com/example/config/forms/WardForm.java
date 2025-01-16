package com.example.config.forms;

import com.example.config.requests.WardRequest;
import com.example.config.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ward_forms")
public class WardForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Ім'я не може бути порожнім")
    @Size(max = 100, message = "Ім'я не повинно перевищувати 100 символів")
    private String firstName;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Прізвище не може бути порожнім")
    @Size(max = 100, message = "Прізвище не повинно перевищувати 100 символів")
    private String lastName;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Email не може бути порожнім")
    @Email(message = "Введіть дійсний email")
    private String email;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Контактний номер не може бути порожнім")
    @Size(max = 15, message = "Контактний номер не повинен перевищувати 15 символів")
    private String contactNumber;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Досвід не може бути порожнім")
    @Size(max = 15, message = "Досвід не повинен перевищувати 15 символів")
    private String experience;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Вид не може бути порожнім")
    @Size(max = 15, message = "Вид не повинен перевищувати 15 символів")
    private String typeOfAnimal;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Ім'я тварини не може бути порожнім")
    @Size(max = 15, message = "Ім'я тварини не повинен перевищувати 15 символів")
    private String animalName;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Вік не може бути порожнім")
    @Size(max = 15, message = "Вік не повинен перевищувати 15 символів")
    private String animalAge;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Стать не може бути порожнім")
    @Size(max = 15, message = "Стать не повинен перевищувати 15 символів")
    private String animalSex;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Розмір не може бути порожнім")
    @Size(max = 15, message = "Розмір не повинен перевищувати 15 символів")
    private String animalSize;
//    @JsonBackReference
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
    public WardForm(String firstName, String lastName, String email, String contactNumber, String experience, String typeOfAnimal, String animalName, String animalAge, String animalSex, String animalSize) {
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
//        this.user=user;
    }
    public WardForm() {

    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
