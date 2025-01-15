package com.example.config.users;

import com.example.config.requests.UserRegistrationRequest;
import com.example.config.token.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public void registerUser(UserRegistrationRequest request) {
        User user = new User(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                jwtTokenProvider.generateToken(request.getEmail(),Role.ADMIN),
                Role.ADMIN);
        userRepository.save(user);
    }

    public boolean loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Customer with email " + email + " not found"
            );
        }
        boolean isPasswordValid = passwordEncoder.matches(password, user.getPassword());
        if (isPasswordValid) {
            // Генерація токену після перевірки паролю
            String token = jwtTokenProvider.generateToken(email,user.getRole());
            user.setAuthToken(token);  // Оновлення токену для користувача
            userRepository.save(user); // Зберігаємо оновленого користувача
        }
        return isPasswordValid;
    }
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public boolean checkToken(String token, String email) {
        return jwtTokenProvider.checkUser(token, email);
    }
    public Optional<User> findByLogin(String login) {
        return Optional.ofNullable(userRepository.findByEmail(login));
    }
}

/*
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

    public User registerUser(String email, String password, Role role) {
        if ((userRepository.findByEmail(email)) != null) {
            throw new UserAlreadyExistsException("User with email " + email + " already exists.");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(email, encodedPassword,role);
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

*/