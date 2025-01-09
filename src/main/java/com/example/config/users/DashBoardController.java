//package com.example.config.users;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.security.Principal;
//
//@Controller
//public class DashBoardController {
//    @Autowired
//    private UserRepository userRepository;
//    @GetMapping("/dashboard")
//    public String dashboard(Principal principal, Model model) {
//        User user = userRepository.findByEmail(principal.getName());
//        model.addAttribute("user", user);
//
//        if (user.getRole() == Role.ADMIN) {
//            return "admin-dashboard";
//        } else if (user.getRole() == Role.SHELTER_OWNER) {
//            return "shelter-dashboard";
//        } else {
//            return "user-dashboard";
//        }
//    }
//}
