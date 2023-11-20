package org.polytech.covidapi.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.polytech.covidapi.domain.CentreVaccination;
import org.polytech.covidapi.service.CentreVaccinationService;



@RestController
@RequestMapping(path = "/centre")
public class CentreVaccinationControleur {
    @Autowired
    private CentreVaccinationService CentreVaccinationService;
    //Chercher dans queryparam pourcity
   // /api/centers?city=Nancy
   // /api/center/12
   // /api/centers?city=Nancy&open=true

    // Obtenir tous les centres de vaccination
    @GetMapping(path = "/")
    public List<CentreVaccination> getAllCenter() {
        return CentreVaccinationService.findAll();
    }

    // Obtenir un centre de vaccination par son id
    @GetMapping(path = "/{id}")
    public Optional<CentreVaccination> getCenterbyID(
        @PathVariable("id") Long id) {
        return CentreVaccinationService.findById(id);
    }

    // Obtenir tous les centres de vaccination par ville
    @GetMapping(path = "/")
    public List<CentreVaccination> getCenterByCity(
        @RequestParam("city") String city) {
        return CentreVaccinationService.findAllByCity(city);
    }

    // Obtenir tous les centres de vaccination par code postal
    @GetMapping(path = "/")
    public List<CentreVaccination> getCenterByPostalCode(
        @RequestParam("postalCode") String postalCode) {
        return CentreVaccinationService.findByPostalCode(postalCode);
    }

    // Obtenir un centre de vaccination par son nom
    @GetMapping(path = "/")
    public Optional<CentreVaccination> getCenterByName(
        @RequestParam("name") String name) {
        return CentreVaccinationService.findByName(name);
    }

    // Supprimer un centre de vaccination par son id s'il existe
    @GetMapping(path = "/delete")
    public void deleteCenterById(
        @RequestParam("id") Long id) {
        CentreVaccinationService.deleteById(id);
    }

    // Ajouter un centre de vaccination
    @GetMapping(path = "/add")
    public CentreVaccination addCenter(@RequestBody CentreVaccination centre){
        return CentreVaccinationService.save(centre);
    }
}