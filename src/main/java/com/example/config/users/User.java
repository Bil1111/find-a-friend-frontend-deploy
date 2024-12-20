package com.example.config.users;

import com.example.config.shelters.Shelter;
import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

//    @ManyToOne
//    @JoinColumn(name = "shelter_id", referencedColumnName = "id", nullable = true)
//    private Shelter shelter;

    public User(String email, String encodedPassword) {
        this.email = email;
        this.password = encodedPassword;
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

//    public Shelter getShelter() {
//        return shelter;
//    }
//
//    public void setShelter(Shelter shelter) {
//        this.shelter = shelter;
//    }
}
