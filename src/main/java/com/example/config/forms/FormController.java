package com.example.config.forms;

import com.example.config.requests.AdoptRequest;
import com.example.config.requests.VolunteerRequest;
import com.example.config.requests.WardRequest;
import com.example.config.shelters.Shelter;
import com.example.config.shelters.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/forms")
public class FormController {
    @Autowired
    private FormService formService;
    @Autowired
    private ShelterRepository shelterRepository;

    @PostMapping("/volunteer")
    public ResponseEntity<String> volunteerForm(@RequestBody VolunteerRequest request) {
        Shelter shelter = shelterRepository.findById(request.getShelter())
                .orElseThrow(() -> new RuntimeException("Shelter not found"));
        formService.addVolunteerForm(request, shelter);
        return new ResponseEntity<>(HttpStatus.CREATED);


    }

    @PostMapping("/adopt")
    public ResponseEntity<Map<String, String>> adoptForm(@RequestBody AdoptRequest request) {
        formService.addAdoptForm(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/ward")
    public ResponseEntity<Map<String, String>> wardForm(@RequestBody WardRequest request) {
        formService.addWardForm(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
