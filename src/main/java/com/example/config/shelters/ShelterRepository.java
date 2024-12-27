package com.example.config.shelters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    List<Shelter> findByCity(String city);
    List<Shelter> findByAnimalsNotEmpty(); // Example method to find shelters with available animals
    List<Shelter> findByNameContainingIgnoreCase(String name);
    boolean existsById(Long id);
}
