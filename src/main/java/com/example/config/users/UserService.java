package com.example.config.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String email, String password) {
        if ((userRepository.findByEmail(email)) != null) {
            throw new UserAlreadyExistsException("User with email " + email + " already exists.");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(email, encodedPassword);
        return userRepository.save(user);
    }

    public boolean loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }
        return passwordEncoder.matches(password, user.getPassword());
    }

    public Optional<User> findByLogin(String login) {
        return Optional.ofNullable(userRepository.findByEmail(login));
    }
}
