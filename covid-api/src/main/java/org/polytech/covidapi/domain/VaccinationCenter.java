package org.polytech.covidapi.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    
    private String postalCode;
    private String address;
    private String city;

    // @OneToMany
    @JsonIgnoreProperties("vaccinationCenter")
    @OneToMany(mappedBy = "vaccinationCenter")
    private List<Users> users;

    @JsonIgnoreProperties("vaccinationCenter")
    @OneToMany(mappedBy = "vaccinationCenter", orphanRemoval = true)
    private List<Rdv> rdv = new ArrayList<>(); ;


    // GETTERS & SETTERS
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

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Rdv> getRdv() {
        return rdv;
    }

    public void setRdv(List<Rdv> rdv) {
        this.rdv = rdv;
    }    
    
    // TO STRING
    @Override
    public String toString() {
        return "CentreVaccination [id=" + id + ", name=" + name + ", postalCode=" + postalCode + ", address=" + address + ", city=" + city + "]";
    }
}
