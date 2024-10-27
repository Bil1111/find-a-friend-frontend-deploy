package com.example.config.controllers;

import com.example.config.map.Establishment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EstablishmentController {

    @GetMapping("/api/controllers/establishments")
    public List<Establishment> getEstablishments() {
        return List.of(
                new Establishment("Притулок для студентів", 49.233657, 28.411085, "Хмельницьке шосе, 95, Вінниця, Вінницька область, Україна, 21000", "+380 432 560 848", "https://scontent.fprg1-1.fna.fbcdn.net/v/t39.30808-6/344722087_764702611936308_3924215860049750228_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=cc71e4&_nc_ohc=1ArPRKT0wi4Q7kNvgFcmKMb&_nc_zt=23&_nc_ht=scontent.fprg1-1.fna&_nc_gid=Aegn7zMzjqEynst4WYd0G08&oh=00_AYDl9oKribsDPBNrIKJAU0UuaO3tlRqJp1rncaUbOGCQEg&oe=6723B603"),
                new Establishment("Заклад 2", 50.4511, 30.5244, "Адреса 2", "987-654-321", "URL_зображення_2")
        );
    }
}

