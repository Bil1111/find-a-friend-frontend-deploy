package com.example.config.requests;

import jakarta.validation.constraints.*;
public class AnimalRequest {

    @NotEmpty(message = "Ім'я не може бути порожнім")
    @Size(max = 50, message = "Ім'я не повинно перевищувати 50 символів")
    private String name;

    @NotEmpty(message = "Тип не може бути порожнім")
    private String type;

    @NotNull(message = "Вік не може бути порожнім")
    private Integer age;

    @NotEmpty(message = "Розмір не може бути порожнім")
    private String size;

    @NotNull(message = "Shelter ID is required")
    private Long shelter;

    @Size(max = 255, message = "Опис не повинен перевищувати 255 символів")
    private String description;

    @NotNull(message = "Sex is required")
    private Integer sex;
    @NotNull(message = "File")
    private String imageURL;

    // Конструктор без параметрів
    public AnimalRequest() {}

    // Конструктор з параметрами
    public AnimalRequest(String name, String type, Integer age, String size, Long shelter, String description, Integer sex, String imageURL) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.size = size;
        this.shelter = shelter;
        this.description = description;
        this.sex = sex;
        this.imageURL = imageURL;
    }

    // Геттери та сеттери
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

    public Long getShelter() {
        return shelter;
    }

    public void setShelter(Long shelter) { // Змінено з setShelter на setShelter
        this.shelter = shelter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSex(Integer sex){
        this.sex = sex;
    }
    public Integer getSex(){
        return sex;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}