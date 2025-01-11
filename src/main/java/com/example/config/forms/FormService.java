package com.example.config.forms;

import com.example.config.requests.AdoptRequest;
import com.example.config.requests.VolunteerRequest;
import com.example.config.shelters.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService {
    @Autowired
    private AdoptFormRepository adoptFormRepository;
    @Autowired
    private VolunteerFormRepository volunteerFormRepository;

    public void addVolunteerForm(VolunteerRequest request, Shelter shelter) {
        VolunteerForm volunteerForm = new VolunteerForm(request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getContactNumber(),
                shelter);
        volunteerFormRepository.save(volunteerForm);
    }

    public void addAdoptForm(AdoptRequest request, Shelter shelter) {
        AdoptForm adoptForm = new AdoptForm(request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getContactNumber(),
                request.getTypeOfAnimal(),
                request.getExperience(),
                shelter);
        adoptFormRepository.save(adoptForm);
    }

}
