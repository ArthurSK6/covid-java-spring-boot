package org.polytech.covidapi.web;


import java.util.List;
import java.util.Optional;

import org.polytech.covidapi.domain.Users;
import org.polytech.covidapi.domain.VaccinationCenter;
import org.polytech.covidapi.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "/api")
public class VaccinationCenterControleur {
    @Autowired
    private VaccinationCenterService centreVaccinationService;

    // Définition variable URL public
    private static final String URL_PUBLIC = "/public/centre";

    // Définition variable URL superadmin
    private static final String URL_SUPERADMIN = "/superadmin/centre";

    // Définition variable URL admin
    private static final String URL_ADMIN = "/admin/centre";

    // Obtenir tous les centres de vaccination
    @GetMapping(path = URL_PUBLIC + "/all")
    public List<VaccinationCenter> getAllCenter() {
        return centreVaccinationService.findAll();
    }

    // Obtenir un centre de vaccination par son id
    @GetMapping(path = URL_PUBLIC + "/id/{id}")
    public Optional<VaccinationCenter> getCenterById(
        @PathVariable("id") Long id) {
        return centreVaccinationService.findById(id);
    }

    // Obtenir tous les centres de vaccination par ville
    @GetMapping(path = URL_PUBLIC + "/city/{city}")
    public List<VaccinationCenter> getAllCenterByCity(
        @PathVariable("city") String city) {
        return centreVaccinationService.findAllByCity(city);
    }

    // Obtenir tous les centres de vaccination par code postal
    @GetMapping(path = URL_PUBLIC + "/postalCode/{postalCode}")
    public List<VaccinationCenter> getAllCenterByPostalCode(
        @PathVariable("postalCode") String postalCode) {
        return centreVaccinationService.findAllByPostalCode(postalCode);
    }

    // Obtenir un centre de vaccination par son nom
    @GetMapping(path = URL_PUBLIC + "/name/{name}")
    public Optional<VaccinationCenter> getCenterByName(
        @PathVariable("name") String name) {
        return centreVaccinationService.findByName(name);
    }

    // Lier un utilisateur (id) à un centre de vaccination (id)
    @PutMapping(path = URL_ADMIN + "/utilisateur/add")
    public boolean linkUserToVaccinationCenter(
        @RequestParam("idUser") Long idUser,
        @RequestParam("idCenter") Long idCenter) {
        return centreVaccinationService.linkUserToVaccinationCenter(idUser,idCenter);
    }

    // Obtenir tous les utilisateurs d'un centre de vaccination
    @GetMapping(path = URL_ADMIN + "/utilisateur/{idCenter}")
    public List<Users> getAllUsersByVaccinationCenter(
        @PathVariable("idCenter") Long idCenter) {
        return centreVaccinationService.findAllUsersByVaccinationCenterById(idCenter);
    }

    // Supprimer un centre de vaccination par son id s'il existe
    @DeleteMapping(path = URL_SUPERADMIN + "/delete/{id}")
    public boolean deleteCenterById(
        @PathVariable("id") Long id) {
        return centreVaccinationService.deleteById(id);
    }

    // Ajouter un centre de vaccination
    @PostMapping(path = URL_SUPERADMIN + "/add")
    public VaccinationCenter addCenter(@RequestBody VaccinationCenter centre){
        return centreVaccinationService.save(centre);
    }

    // Mettre à jour un centre de vaccination
    @Transactional
    @PutMapping(path = URL_SUPERADMIN + "/update")
    public VaccinationCenter updateCenter(@RequestBody VaccinationCenter centre){
        return centreVaccinationService.save(centre);
    }
}