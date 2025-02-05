package com.example.config.shelters;

import com.example.config.animals.Animal;
import com.example.config.forms.VolunteerForm;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "shelter")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Назва не може бути порожньою")
    @Size(max = 100, message = "Назва не повинна перевищувати 100 символів")
    private String name;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Адреса не може бути порожньою")
    private String address;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Контактний номер не може бути порожнім")
    @Size(max = 15, message = "Контактний номер не повинен перевищувати 15 символів")
    private String contactNumber;

    @Column(columnDefinition = "VARCHAR(255)")
    @Size(max = 500, message = "Опис не повинен перевищувати 500 символів")
    private String description;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Місто не може бути порожнім")
    private String city;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotNull(message = "Latitude cannot be null")
    private Double latitude;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotNull(message = "Longitude cannot be null")
    private Double longitude;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotNull(message = "File")
    private String imageURL;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotEmpty(message = "Email не може бути порожнім")
    @Email(message = "Невірний формат email")
    private String email;

    @Column(columnDefinition = "VARCHAR(255)")
    @Size(max = 50, message = "Номер картки не повинен перевищувати 50 символів")
    private String card;

    @Column(columnDefinition = "VARCHAR(255)")
    @Size(max = 50, message = "PayPal не повинен перевищувати 50 символів")
    private String paypal;

    @Column(columnDefinition = "VARCHAR(255)")
    @Size(max = 50, message = "IBAN не повинен перевищувати 50 символів")
    private String iban;
    @JsonManagedReference
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Animal> animals;

    public Shelter(String name, String address, String contactNumber, String description, String city, Double latitude, Double longitude, String imageURL, String email, String card, String paypal, String iban) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.description = description;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageURL = imageURL;
        this.email = email;
        this.card = card;
        this.paypal = paypal;
        this.iban = iban;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getPaypal() {
        return paypal;
    }

    public void setPaypal(String paypal) {
        this.paypal = paypal;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}