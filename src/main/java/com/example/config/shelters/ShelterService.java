package com.example.config.shelters;

import com.example.config.requests.ShelterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    public void addShelter(ShelterRequest request) {
        Shelter shelter = new Shelter(
                request.getName(),
                request.getAddress(),
                request.getContactNumber(),
                request.getDescription(),
                request.getCity(),
                request.getLatitude(),
                request.getLongitude(),
                request.getImageURL()
        );
        shelterRepository.save(shelter);
    }

    public Optional<Shelter> getShelterById(Long id) {
        return shelterRepository.findById(id);
    }

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }
    public List<Shelter> searchSheltersByName(String query) {
        return shelterRepository.findByNameContainingIgnoreCase(query);
    }
    public void deleteShelter(Long id) {
        if (shelterRepository.existsById(id)) {
            shelterRepository.deleteById(id);
        } else {
            throw new ShelterNotFoundException("Shelter with ID " + id + " not found.");
        }
    }
}
