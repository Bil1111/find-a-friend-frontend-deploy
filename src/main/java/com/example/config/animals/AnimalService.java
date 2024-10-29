package com.example.config.animals;


import com.example.config.DTO.AnimalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public Animal addAnimal(AnimalRequest request) {
        Animal animal = new Animal(
                request.getName(),
                request.getType(),
                request.getAge(),
                request.getSize(),
                request.getDescription(),
                request.getShelter()
        );
        return animalRepository.save(animal);
    }

    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public void deleteAnimal(Long id) {
        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
        } else {
            throw new AnimalNotFoundException("Animal with ID " + id + " not found.");
        }
    }
}
