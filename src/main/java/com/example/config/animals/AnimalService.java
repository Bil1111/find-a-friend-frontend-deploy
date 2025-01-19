package com.example.config.animals;

import com.example.config.requests.AnimalRequest;
import com.example.config.shelters.Shelter;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
        animal.setShelter(shelter);
        if (request.getSex() == 0) {
            animal.setSex("Хлопчик");
        } else if (request.getSex() == 1) {
            animal.setSex("Дівчинка");
        }
        animal.setCity(shelter.getCity());
        animal.setShelterPhoneNumber(shelter.getContactNumber());
        animal.setImageURL(request.getImageURL());
        animalRepository.save(animal);

    }

    public void updateAnimalDetails(AnimalRequest request, Long animalId, Shelter shelter) {
        Optional<Animal> animalOptional = animalRepository.findById(animalId);

        animalOptional.ifPresentOrElse(animal -> {
            if (StringUtils.isNotBlank(request.getName())) {
                animal.setName(request.getName());
            }
            if (StringUtils.isNotBlank(request.getType())) {
                animal.setName(request.getType());
            }
            if (request.getAge() != null) {
                animal.setAge(request.getAge());
            }
            if (StringUtils.isNotBlank(request.getSize())) {
                animal.setSize(request.getSize());
            }
            if (StringUtils.isNotBlank(request.getDescription())) {
                animal.setDescription(request.getDescription());
            }
            if (request.getShelter() != null) {
                animal.setShelter(shelter);
            }
            if (request.getSex() != null) {
                if (request.getSex() == 0) {
                    animal.setSex("Хлопчик");
                } else if (request.getSex() == 1) {
                    animal.setSex("Дівчинка");
                }
            }
            animalRepository.save(animal);
        }, () -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Animal with id " + animalId + " not found"
            );
        });
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

    public List<Animal> getNextAnimals(int index) {
        List<Animal> animals = new ArrayList<>();
        animalRepository.findAll().forEach(animal -> animals.add(animal));

        List<Animal> nextAnimals = new ArrayList<>();
        if ((index + 10 < animals.size())) {
            for (int i = index; i < index + 10; i++) {
                nextAnimals.add(animals.get(i));
            }
            return nextAnimals;
        } else {
            try {
                for (int i = index; i < index + 10; i++) {
                    nextAnimals.add(animals.get(i));
                }                                                   //TODO пофіксити затичку
            } catch (Exception e) {
                return nextAnimals;
            }
        }
        if (!(index + 10 < animals.size())) {
            for (int i = index; i < index + 10; i++) {
                nextAnimals.add(animals.get(i));
            }

        }
        return nextAnimals;
    }
}
