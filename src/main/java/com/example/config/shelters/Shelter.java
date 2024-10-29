package com.example.config.shelters;

import com.example.config.animals.Animal;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "shelter")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Назва не може бути порожньою")
    @Size(max = 100, message = "Назва не повинна перевищувати 100 символів")
    private String name;

    @NotEmpty(message = "Адреса не може бути порожньою")
    private String address;

    @NotEmpty(message = "Контактний номер не може бути порожнім")
    @Size(max = 15, message = "Контактний номер не повинен перевищувати 15 символів")
    private String contactNumber;

    @Size(max = 500, message = "Опис не повинен перевищувати 500 символів")
    private String description;

    @NotEmpty(message = "Місто не може бути порожнім")
    private String city;

    private double latitude;

    private double longitude;
    @JsonManagedReference
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Animal> animals;

    public Shelter(String name, String address, String contactNumber, String description,String city,double latitude,double longitude) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.description = description;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Shelter() {

    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}