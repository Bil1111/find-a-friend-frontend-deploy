package com.example.config.shelters;

import com.example.config.DTO.ShelterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    public Shelter addShelter(ShelterRequest request) {
        Shelter shelter = new Shelter(
                request.getName(),
                request.getAddress(),
                request.getContactNumber(),
                request.getDescription()
        );
        return shelterRepository.save(shelter);
    }

    public Optional<Shelter> getShelterById(Long id) {
        return shelterRepository.findById(id);
    }

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    public void deleteShelter(Long id) {
        if (shelterRepository.existsById(id)) {
            shelterRepository.deleteById(id);
        } else {
            throw new ShelterNotFoundException("Shelter with ID " + id + " not found.");
        }
    }
}
