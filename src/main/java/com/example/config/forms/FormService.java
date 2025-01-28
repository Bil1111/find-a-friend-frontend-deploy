package com.example.config.forms;

import com.example.config.requests.AdoptRequest;
import com.example.config.requests.VolunteerRequest;
import com.example.config.requests.WardRequest;
import com.example.config.shelters.Shelter;
import com.example.config.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormService {
    @Autowired
    private AdoptFormRepository adoptFormRepository;
    @Autowired
    private VolunteerFormRepository volunteerFormRepository;
    @Autowired
    private WardFormRepository wardFormRepository;

    public void addVolunteerForm(VolunteerRequest request, Shelter shelter, String shelterName) {
        VolunteerForm volunteerForm = new VolunteerForm(request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getContactNumber(),
                shelter,
                shelterName);
        volunteerFormRepository.save(volunteerForm);
    }
    public Optional<VolunteerForm> getVolunteerFormById(Long id) {
        return volunteerFormRepository.findById(id);
    }

    public List<VolunteerForm> getAllVolunteerForms() {
        return volunteerFormRepository.findAll();
    }
    public void addAdoptForm(AdoptRequest request, Shelter shelter, String shelterName) {
        AdoptForm adoptForm = new AdoptForm(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getContactNumber(),
                request.getExperience(),
                request.getTypeOfAnimal(),
                request.getAnimalName(),
                request.getAnimalAge(),
                request.getAnimalSex(),
                request.getAnimalSize(),
                shelter,
                shelterName
        );
        adoptFormRepository.save(adoptForm);
    }
    public Optional<AdoptForm> getAdoptFormById(Long id) {
        return adoptFormRepository.findById(id);
    }

    public List<AdoptForm> getAllAdoptForms() {
        return adoptFormRepository.findAll();
    }
    public void addWardForm(WardRequest request, Shelter shelter, String shelterName) {
        WardForm wardForm=new WardForm(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getContactNumber(),
                request.getExperience(),
                request.getTypeOfAnimal(),
                request.getAnimalName(),
                request.getAnimalAge(),
                request.getAnimalSex(),
                request.getAnimalSize(),
                shelter,
                shelterName);

        wardFormRepository.save(wardForm);
    }
    public Optional<WardForm> getWardFormById(Long id) {
        return wardFormRepository.findById(id);
    }

    public List<WardForm> getAllWardForms() {
        return wardFormRepository.findAll();
    }
}
