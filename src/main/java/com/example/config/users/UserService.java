package com.example.config.users;

import com.example.config.requests.UserRegistrationRequest;
import com.example.config.token.JwtTokenProvider;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateCustomerDetails(UserRegistrationRequest request, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        userOptional.ifPresentOrElse(user -> {
            if (StringUtils.isNotBlank(request.getEmail())) {
                user.setEmail(request.getEmail());
            }
            if (StringUtils.isNotBlank(request.getPassword())) {
                user.setPassword(request.getPassword());
            }
            userRepository.save(user);
        }, () -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with id " + userId + " not found"
            );
        });
    }
    public void registerUser(UserRegistrationRequest request) {
        // Створюємо нового користувача без токена
        User user = new User(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER // Роль користувача
        );

        // Зберігаємо користувача в базу
        userRepository.save(user);

        // Генеруємо токен
        String token = jwtTokenProvider.generateToken(user); // передаємо об'єкт користувача

        // Зберігаємо токен в базі даних
        user.setAuthToken(token); // Зберігаємо токен в користувача
        userRepository.save(user); // Оновлюємо користувача в базі
    }

    public void registerAdmin(UserRegistrationRequest request) {
        User user = new User(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Role.ADMIN
        );
        userRepository.save(user);
        String token = jwtTokenProvider.generateToken(user);
        user.setAuthToken(token);
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
            String token = jwtTokenProvider.generateToken(user);
            user.setAuthToken(token);  // Оновлення токену для користувача
            userRepository.save(user); // Зберігаємо оновленого користувача
        }
        return isPasswordValid;
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public Optional<User> findByLogin(String login) {
        return Optional.ofNullable(userRepository.findByEmail(login));
    }
}
