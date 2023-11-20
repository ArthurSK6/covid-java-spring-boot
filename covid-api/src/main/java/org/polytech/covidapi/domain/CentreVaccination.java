package org.polytech.covidapi.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class CentreVaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String postalCode;
    private String address;
    private String city;

    @OneToMany
    @JsonIgnoreProperties("centreVaccination")
    private List<Utilisateurs> utilisateurs;

/*
    // Constructeurs
    public CentreVaccination() {
    }

    public CentreVaccination(String name, String postalCode, String address, String city) {
    this.name = name;
    this.postalCode = postalCode;
    this.address = address;
    this.city = city;
    }
 */

    // Getters et Setters

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


    public String getPostalCode() {
        return postalCode;
    }


    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CentreVaccination [id=" + id + ", name=" + name + ", postalCode=" + postalCode + ", address=" + address
                + ", city=" + city + "]";
    }    
    
    
}
