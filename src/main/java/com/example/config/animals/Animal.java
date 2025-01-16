package com.example.config.animals;

import com.example.config.shelters.Shelter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
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
    @Column(columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Тип не може бути порожнім")
    private String type;

    @NotNull(message = "Вік не може бути порожнім")
    private Integer age;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Розмір не може бути порожнім")
    private String size;

    @Column(columnDefinition = "VARCHAR(255)")
    @Size(max = 255, message = "Опис не повинен перевищувати 255 символів")
    private String description;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotNull(message = "Sex is required")
    private String sex;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotNull(message = "File")
    private String imageURL;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotNull(message = "City is required")
    private String city;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotNull(message = "Shelter Phone Number is required")
    private String shelterPhoneNumber;

    @JsonBackReference
    @ManyToOne // Вказуємо, що багато тварин можуть бути у одного притулку
    @JoinColumn(name = "shelter_id", nullable = false) // Зазначаємо, що поле shelter_id в базі даних
    private Shelter shelter; // Вказуємо зв'язок з притулком

    // Конструктори
    public Animal(String name, String type, Integer age, String size, String description, Shelter shelter, int sexIndex) {

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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShelterPhoneNumber() {
        return shelterPhoneNumber;
    }

    public void setShelterPhoneNumber(String shelterPhoneNumber) {
        this.shelterPhoneNumber = shelterPhoneNumber;
    }

    public void setImageUrl(String s) {
    }

    
    
}
