package com.example.config.controllers;

import com.example.config.users.User;
import com.example.config.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/users/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User()); // Пустий об'єкт User для форми
        return "register";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        // Перевірка на наявність помилок у введених даних
        if (result.hasErrors()) {
            return "register";
        }

        // Перевірка, чи існує користувач з таким email
        if (userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("errorMessage", "Email вже зареєстрований");
            return "register";
        }

        // Збереження користувача
        userRepository.save(user);
        return "redirect:/login"; // Переадресація на сторінку входу після успішної реєстрації
    }
}
