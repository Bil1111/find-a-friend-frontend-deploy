package com.example.config.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginRequest {

    @Email(message = "Вкажіть валідний email")
    @NotBlank(message = "Email не може бути порожнім")
    private String email;

    @NotBlank(message = "Пароль не може бути порожнім")
    @Size(min = 6, max = 20, message = "Пароль має бути від 6 до 20 символів")
    private String password;

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
}
