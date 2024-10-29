package com.example.config.animals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByType(String type);
    List<Animal> findByAge(int age);
    List<Animal> findBySize(String size);
    List<Animal> findByShelterId(Long shelterId);
    boolean existsById(Long id);
}
