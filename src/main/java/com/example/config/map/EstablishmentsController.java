package com.example.config.map;

import com.example.config.shelters.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class EstablishmentsController {
    @Autowired
    private ShelterService shelterService;

    //    @GetMapping("/api/mapPoints")
//    public ResponseEntity<String> getMapPoints() {
//        try {
//            Path path = new Path.of("shelters.json") {
//            }.getFile().toPath();
//            String json = Files.readString(path);
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Type", "application/json");
//            return new ResponseEntity<>(json, headers, HttpStatus.OK);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
//        }
//    }
    @GetMapping("/api/mapPoints")
    public ResponseEntity<String> getMapPoints() {
        try {
            shelterService.saveSheltersToJsonFile();

            Path path = Path.of("shelters.json");

            String json = Files.readString(path);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");

            // Повернення JSON-відповіді
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
    }

}