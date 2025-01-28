package com.example.config.users;
import jakarta.persistence.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Email не може бути порожнім")
    @Email(message = "Введіть дійсний email")
    private String email;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Пароль не може бути порожнім")
    @Size(min = 6, message = "Пароль має містити не менше 6 символів")
    private String password;

    @Column(unique = true)
    private String authToken; // Унікальний токен для аутентифікації
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String email, String encodedPassword, Role role) {
        this.email = email;
        this.password = encodedPassword;
        // this.authToken = authToken;
        this.role=role;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    //    public Shelter getShelter() {
//        return shelter;
//    }
//
//    public void setShelter(Shelter shelter) {
//        this.shelter = shelter;
//    }
}
