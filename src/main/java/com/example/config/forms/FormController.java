package com.example.config.forms;

import com.example.config.requests.AdoptRequest;
import com.example.config.requests.VolunteerRequest;
import com.example.config.requests.WardRequest;
import com.example.config.shelters.Shelter;
import com.example.config.shelters.ShelterRepository;
import com.example.config.shelters.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/forms")
public class FormController {
    @Autowired
    private FormService formService;
    @Autowired
    private ShelterService shelterService;
    @Autowired
    private ShelterRepository shelterRepository;

    @PostMapping("/volunteer")
    public ResponseEntity<String> volunteerForm(@RequestBody VolunteerRequest request) {
        Shelter shelter = shelterRepository.findById(request.getShelter())
                .orElseThrow(() -> new RuntimeException("Shelter not found"));

        formService.addVolunteerForm(request, shelter,shelterService.getShelterNameById(request.getShelter()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/volunteer/all")
    public ResponseEntity<List<VolunteerForm>> getAllVolunteerForms() {
        List<VolunteerForm> volunteerForms = formService.getAllVolunteerForms();
        return new ResponseEntity<>(volunteerForms, HttpStatus.OK);
    }

    @GetMapping("/volunteer/{id}")
    public ResponseEntity<VolunteerForm> getVolunteerFormById(@PathVariable("id") Long id) {
        Optional<VolunteerForm> volunteerForm = formService.getVolunteerFormById(id);
        return volunteerForm.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/volunteer/{id}")
    public ResponseEntity<String> deleteVolunteerForm(@PathVariable("id") Long id) {
        formService.deleteVolunteerForm(id);
        return new ResponseEntity<>("Volunteer form deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/adopt")
    public ResponseEntity<Map<String, String>> adoptForm(@RequestBody AdoptRequest request) {
        Shelter shelter = shelterRepository.findById(request.getShelter())
                .orElseThrow(() -> new RuntimeException("Shelter not found"));
        formService.addAdoptForm(request,shelter,shelterService.getShelterNameById(request.getShelter()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/adopt/all")
    public ResponseEntity<List<AdoptForm>> getAllAdoptForms() {
        List<AdoptForm> adoptForms = formService.getAllAdoptForms();
        return new ResponseEntity<>(adoptForms, HttpStatus.OK);
    }

    @GetMapping("/adopt/{id}")
    public ResponseEntity<AdoptForm> getAdoptFormById(@PathVariable("id") Long id) {
        Optional<AdoptForm> adoptForm = formService.getAdoptFormById(id);
        return adoptForm.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/adopt/{id}")
    public ResponseEntity<String> deleteAdoptForm(@PathVariable("id") Long id) {
        formService.deleteAdoptForm(id);
        return new ResponseEntity<>("Adopt form deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/ward")
    public ResponseEntity<Map<String, String>> wardForm(@RequestBody WardRequest request) {
        Shelter shelter = shelterRepository.findById(request.getShelter())
                .orElseThrow(() -> new RuntimeException("Shelter not found"));
        formService.addWardForm(request, shelter,shelterService.getShelterNameById(request.getShelter()));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/ward/all")
    public ResponseEntity<List<WardForm>> getAllWardForms() {
        List<WardForm> wardForms = formService.getAllWardForms();
        return new ResponseEntity<>(wardForms, HttpStatus.OK);
    }

    @GetMapping("/ward/{id}")
    public ResponseEntity<WardForm> getWardFormById(@PathVariable("id") Long id) {
        Optional<WardForm> wardForm = formService.getWardFormById(id);
        return wardForm.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/ward/{id}")
    public ResponseEntity<String> deleteWardForm(@PathVariable("id") Long id) {
        formService.deleteWardForm(id);
        return new ResponseEntity<>("Ward form deleted successfully", HttpStatus.OK);
    }
}