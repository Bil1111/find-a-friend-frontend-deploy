package com.example.config.users;

import com.example.config.requests.UserLoginRequest;
import com.example.config.requests.UserRegistrationRequest;
import com.example.config.token.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
        try {
            Optional<User> existingCustomer = userService.findByLogin(request.getEmail());
            if (existingCustomer.isPresent()) {
                return new ResponseEntity<>("User with this email already exists", HttpStatus.CONFLICT);
            }
            userService.registerUser(request);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody UserLoginRequest request) {
        try {
            Optional<User> user = userService.findByEmail(request.getEmail());
            if (user.isPresent() && userService.loginUser(request.getEmail(), request.getPassword())) {
                User loggedInUser = user.get();
                // Генерація токену після успішного входу
                String token = jwtTokenProvider.generateToken(loggedInUser.getEmail(), loggedInUser.getRole()); // Генерація токену
                Map<String, String> response = new HashMap<>();
                response.put("token", token); // Повертаємо токен у відповіді
                if (loggedInUser.getRole().equals(Role.ADMIN)) {
                    response.put("redirect", "/donate"); // Адмін
                } else {
                    response.put("redirect", "/about"); // Звичайний користувач
                }
                return new ResponseEntity<>(response, HttpStatus.OK); // Повертаємо ResponseEntity з Map
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (UserNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage()); // Помилка у форматі Map
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
// DEFAULT
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody UserLoginRequest request) {
//        try {
//
//            if (userService.loginUser(request.getEmail(), request.getPassword())) {
//                System.out.println(userService);
//                return new ResponseEntity<>(HttpStatus.OK);
//            } else {
//                System.out.println("Declined");
//                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//            }
//        } catch (UserNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
/* WITH ROLES
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
        try {
            Role role = request.getRole() != null ? request.getRole() : USER;
            userService.registerUser(request.getEmail(), request.getPassword(), role);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
 */