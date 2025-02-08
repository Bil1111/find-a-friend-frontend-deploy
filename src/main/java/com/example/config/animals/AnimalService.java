package com.example.config.animals;

import com.example.config.requests.AnimalRequest;
import com.example.config.shelters.Shelter;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public void addAnimal(AnimalRequest request, Shelter shelter,String shelterName) {
        // Створення нового Animal
        Animal animal = new Animal();
        animal.setName(request.getName());
        animal.setType(request.getType());
        animal.setAge(request.getAge());
        animal.setSize(request.getSize());
        animal.setDescription(request.getDescription());
        animal.setVaccinated(request.getVaccinated());
        animal.setSterilized(request.getSterilized());
        animal.setSpecialCare(request.getSpecialCare());
        animal.setShelter(shelter);
        animal.setShelterName(shelterName);
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
            if (request.getVaccinated() != null) {
                animal.setVaccinated(request.getVaccinated());
            }
            if (request.getSterilized() != null) {
                animal.setSterilized(request.getSterilized());
            }
            if (request.getSpecialCare() != null) {
                animal.setSpecialCare(request.getSpecialCare());
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

    public List<Animal> getNextAnimals(int startId, int itemsPerPage) {
        List<Animal> animals = new ArrayList<>();
        animalRepository.findAll().forEach(animals::add);  // Завантажуємо всі елементи з бази

        List<Animal> nextAnimals = new ArrayList<>();
        int endId = Math.min(startId + itemsPerPage, animals.size()); // Визначаємо кінець індексу

        // Додаємо елементи між startId і endId
        for (int i = startId; i < endId; i++) {
            nextAnimals.add(animals.get(i));
        }

        return nextAnimals;
    }
}
