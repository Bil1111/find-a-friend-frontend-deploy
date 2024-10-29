package com.example.config.animals;

import jakarta.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.example.config.shelters.Shelter;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Ім'я не може бути порожнім")
    @Size(max = 50, message = "Ім'я не повинно перевищувати 50 символів")
    private String name;

    @NotEmpty(message = "Тип не може бути порожнім") // Наприклад, "кіт", "пес"
    private String type;

    @NotNull(message = "Вік не може бути порожнім")
    private Integer age;

    @NotEmpty(message = "Розмір не може бути порожнім") // Наприклад, "маленький", "середній", "великий"
    private String size;

    @Size(max = 255, message = "Опис не повинен перевищувати 255 символів")
    private String description;

    @ManyToOne
    @JoinColumn(name = "shelter_id", nullable = false)
    private Shelter shelter;

    // Constructor including the new description field
    public Animal(String name, String type, Integer age, String size, String description, Shelter shelter) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.size = size;
        this.description = description;
        this.shelter = shelter;
    }

    // No-argument constructor
    public Animal() {}

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
}
