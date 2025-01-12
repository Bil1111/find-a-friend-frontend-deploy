package com.example.config.forms;

import com.example.config.requests.AdoptRequest;
import com.example.config.requests.VolunteerRequest;
import com.example.config.requests.WardRequest;
import com.example.config.shelters.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService {
    @Autowired
    private AdoptFormRepository adoptFormRepository;
    @Autowired
    private VolunteerFormRepository volunteerFormRepository;
    @Autowired
    private WardFormRepository wardFormRepository;

    public void addVolunteerForm(VolunteerRequest request, Shelter shelter) {
        VolunteerForm volunteerForm = new VolunteerForm(request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getContactNumber(),
                shelter);
        volunteerFormRepository.save(volunteerForm);
    }

    public void addAdoptForm(AdoptRequest request) {
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
                request.getAnimalSize()
               );
        adoptFormRepository.save(adoptForm);
    }
    public void addWardForm(WardRequest request) {
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
                request.getAnimalSize()
        );

        wardFormRepository.save(wardForm);
    }
}
