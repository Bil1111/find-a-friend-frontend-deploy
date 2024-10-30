package com.example.config.animals;

import com.example.config.shelters.Shelter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Ім'я не може бути порожнім")
    @Size(max = 50, message = "Ім'я не повинно перевищувати 50 символів")
    private String name;

    @NotEmpty(message = "Тип не може бути порожнім")
    private String type;

    @NotNull(message = "Вік не може бути порожнім")
    private Integer age;

    @NotEmpty(message = "Розмір не може бути порожнім")
    private String size;

    @Size(max = 255, message = "Опис не повинен перевищувати 255 символів")
    private String description;

    @NotNull(message = "Sex is required")
    private String sex;


    @JsonBackReference
    @ManyToOne // Вказуємо, що багато тварин можуть бути у одного притулку
    @JoinColumn(name = "shelter_id", nullable = false) // Зазначаємо, що поле shelter_id в базі даних
    private Shelter shelter; // Вказуємо зв'язок з притулком

    // Конструктори
    public Animal(String name, String type, Integer age, String size, String description, Shelter shelter, int sexIndex) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.size = size;
        this.description = description;
        this.shelter = shelter; // Присвоюємо об'єкт Shelter
        if(sexIndex == 0){
            sex = "Хлопчик";
        } else if (sexIndex == 1) {
            sex = "Дівчинка";
        }
    }

    public Animal() {}

    // Геттери та сеттери
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
