package com.example.config.forms;

import com.example.config.shelters.Shelter;
import com.example.config.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "volunteer_forms")
public class VolunteerForm {
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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "shelter_id", nullable = false)
    private Shelter shelter;

//    @JsonBackReference
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    public VolunteerForm(String firstName, String lastName, String email, String contactNumber, Shelter shelter) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.shelter = shelter;
//        this.user=user;
    }

    public VolunteerForm() {

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

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
