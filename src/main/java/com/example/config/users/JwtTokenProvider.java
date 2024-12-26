// package com.example.config.users;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.SignatureException;

// import java.util.Date;
// import org.springframework.stereotype.Component;

// @Component
// public class JwtTokenProvider {
//     private final String jwtSecret = "your_secret_key"; // Секретний ключ

//      // Генерація токена
//      @SuppressWarnings("deprecation")
//     public String generateToken(String email) {
//         return Jwts.builder()
//                 .setSubject(email) // Додаємо email як payload
//                 .setIssuedAt(new Date()) // Час створення
//                 .signWith(SignatureAlgorithm.HS512, jwtSecret) // Підпис токена
//                 .compact();
//     }

//     public String getEmailFromToken(String token) {
//         try {
//             @SuppressWarnings("deprecation")
//             Claims claims = Jwts.parser()
//                     .setSigningKey(jwtSecret) // Вказуємо секретний ключ для перевірки підпису
//                     .parseClaimsJws(token) // Розбираємо токен
//                     .getBody(); // Отримуємо тіло токена
//             return claims.getSubject(); // Витягуємо email з поля "subject"
//         } catch (@SuppressWarnings("deprecation") SignatureException e) {
//             // Невірний підпис
//             throw new IllegalArgumentException("Invalid JWT signature");
//         } catch (Exception e) {
//             // Інші помилки (наприклад, термін дії токена закінчився)
//             throw new IllegalArgumentException("Invalid JWT token");
//         }
//     }

//     public boolean checkuser (String token, String email){
//         String tEmail = getEmailFromToken(token);
//         return tEmail.equals(email);
//     }
// }
