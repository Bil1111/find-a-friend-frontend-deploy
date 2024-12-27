package com.example.config.map;

import com.example.config.shelters.Shelter;
import com.example.config.shelters.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class SearchOfSheltersController {
    @Autowired
    private ShelterService shelterService;

    @GetMapping("/api/shelters/search")
    public List<Shelter> searchShelters(@RequestParam("query") String query) {
        return shelterService.searchSheltersByName(query);
    }
}
