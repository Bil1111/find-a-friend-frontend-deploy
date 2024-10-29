package com.example.config.animals;

import com.example.config.DTO.AnimalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping("/add")
    public ResponseEntity<String> addAnimal(@RequestBody AnimalRequest request) {
        animalService.addAnimal(request);
        return new ResponseEntity<>("Animal added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        Optional<Animal> animal = animalService.getAnimalById(id);
        return animal.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return new ResponseEntity<>("Animal deleted successfully", HttpStatus.OK);
    }
}
