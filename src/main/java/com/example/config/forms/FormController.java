package com.example.config.forms;

import com.example.config.requests.AdoptRequest;
import com.example.config.requests.VolunteerRequest;
import com.example.config.requests.WardRequest;
import com.example.config.shelters.Shelter;
import com.example.config.shelters.ShelterRepository;
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
    private ShelterRepository shelterRepository;

//    @PostMapping("/volunteer")
//    public ResponseEntity<String> volunteerForm(@RequestBody VolunteerRequest request) {
//        Shelter shelter = shelterRepository.findById(request.getShelter())
//                .orElseThrow(() -> new RuntimeException("Shelter not found"));
//        User user = userRepository.findById(request.getUser())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        formService.addVolunteerForm(request, shelter, user);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
    @PostMapping("/volunteer")
    public ResponseEntity<String> volunteerForm(@RequestBody VolunteerRequest request) {
        Shelter shelter = shelterRepository.findById(request.getShelter())
                .orElseThrow(() -> new RuntimeException("Shelter not found"));
//        Long userId = jwtTokenProvider.getUserIdFromToken(token.substring(7));
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        formService.addVolunteerForm(request, shelter);
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

//    @PostMapping("/adopt")
//    public ResponseEntity<Map<String, String>> adoptForm(@RequestBody AdoptRequest request) {
//        User user = userRepository.findById(request.getUser())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        formService.addAdoptForm(request, user);
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//   }
    @PostMapping("/adopt")
    public ResponseEntity<Map<String, String>> adoptForm(@RequestBody AdoptRequest request) {
//        System.out.println("Received token: " + token);
//        Long userId = jwtTokenProvider.getUserIdFromToken(token.substring(7)); // Витягуємо токен без "Bearer "
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        formService.addAdoptForm(request);

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

//    @PostMapping("/ward")
//    public ResponseEntity<Map<String, String>> wardForm(@RequestBody WardRequest request) {
//        User user = userRepository.findById(request.getUser())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        formService.addWardForm(request, user);
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
    @PostMapping("/ward")
    public ResponseEntity<Map<String, String>> wardForm(@RequestBody WardRequest request) {
//        Long userId = jwtTokenProvider.getUserIdFromToken(token.substring(7));
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        formService.addWardForm(request);

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
}