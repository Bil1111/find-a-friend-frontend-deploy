package com.example.config.animals;

import com.example.config.requests.AnimalRequest;
import com.example.config.shelters.Shelter;
import com.example.config.shelters.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Value("src/main/resources/images}")
    private String uploadDir;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private ShelterRepository shelterRepository; // Додайте поле для ShelterRepository

    @PostMapping("/add")
    public ResponseEntity<String> addAnimal(@RequestBody AnimalRequest request) {
        Shelter shelter = shelterRepository.findById(request.getShelter())
                .orElseThrow(() -> new RuntimeException("Shelter not found"));
        animalService.addAnimal(request, shelter);

        return new ResponseEntity<>("Animal added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable("id") Long id) {
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
    public ResponseEntity<String> deleteAnimal(@PathVariable("id") Long id) {
        animalService.deleteAnimal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping({"/next/{id}"})
    public ResponseEntity<List<Animal>> getNextAnimal(@PathVariable("id") int id) {
        try{
            List<Animal> animals = animalService.getNextAnimals(id);
            return new ResponseEntity<>(animals, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
