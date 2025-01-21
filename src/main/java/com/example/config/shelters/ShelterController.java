package com.example.config.shelters;

import com.example.config.animals.Animal;
import com.example.config.requests.AnimalRequest;
import com.example.config.requests.ShelterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shelters")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @PostMapping("/add")
    public ResponseEntity<String> addShelter(@RequestBody ShelterRequest request) {
        shelterService.addShelter(request);
        return new ResponseEntity<>("Shelter added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shelter> getShelterById(@PathVariable("id") Long id) {
        Optional<Shelter> shelter = shelterService.getShelterById(id);
        return shelter.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{shelterId}/animals")
    public ResponseEntity<List<Animal>> getShelterAnimalsById(@PathVariable("shelterId") Long shelterId) {
        List<Animal> animals = shelterService.getShelterAnimalsById(shelterId);

        if (animals == null || animals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Якщо тварин не знайдено
        }

        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @GetMapping("/{shelterId}/animals/next/{startId}")
    public ResponseEntity<List<Animal>> getNextAnimal(@PathVariable("shelterId") Long shelterId,
                                                      @PathVariable("startId") int startId) {
        try {
            List<Animal> animals = shelterService.getNextAnimals(shelterId, startId, 10);

            if (animals == null || animals.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Якщо немає тварин
            }

            return new ResponseEntity<>(animals, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomerDetails(@RequestBody ShelterRequest request, @PathVariable("id") Long id) {
        shelterService.updateShelterDetails(request, id);
        return new ResponseEntity<>("Shelter updated successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Shelter>> getAllShelters() {
        List<Shelter> shelters = shelterService.getAllShelters();
        return new ResponseEntity<>(shelters, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShelter(@PathVariable("id") Long id) {
        shelterService.deleteShelter(id);
        return new ResponseEntity<>("Shelter deleted successfully", HttpStatus.OK);
    }
}
