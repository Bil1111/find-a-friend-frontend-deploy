package com.example.config.animals;

import com.example.config.DTO.AnimalRequest;
import com.example.config.shelters.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public void addAnimal(AnimalRequest request, Shelter shelter) {
        // Створення нового Animal
        Animal animal = new Animal();
        animal.setName(request.getName());
        animal.setType(request.getType());
        animal.setAge(request.getAge());
        animal.setSize(request.getSize());
        animal.setDescription(request.getDescription());
        animal.setShelter(shelter); // Встановлення shelter

        // Збереження тварини в базі даних
        animalRepository.save(animal);
    }

    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}
