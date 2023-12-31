package org.polytech.covidapi.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.polytech.covidapi.domain.Users;
import org.polytech.covidapi.domain.VaccinationCenter;
import org.polytech.covidapi.service.VaccinationCenterService;



@RestController
@RequestMapping(path = "/api/centre")
public class VaccinationCenterControleur {
    @Autowired
    private VaccinationCenterService centreVaccinationService;
    //Chercher dans queryparam pourcity
   // /api/centers?city=Nancy
   // /api/center/12
   // /api/centers?city=Nancy&open=true

    // Obtenir tous les centres de vaccination
    @GetMapping(path = "/all")
    public List<VaccinationCenter> getAllCenter() {
        return centreVaccinationService.findAll();
    }

    // Obtenir un centre de vaccination par son id
    @GetMapping(path = "/id/{id}")
    public Optional<VaccinationCenter> getCenterById(
        @PathVariable("id") Long id) {
        return centreVaccinationService.findById(id);
    }

    // Obtenir tous les centres de vaccination par ville
    @GetMapping(path = "/city/{city}")
    public List<VaccinationCenter> getAllCenterByCity(
        @PathVariable("city") String city) {
        return centreVaccinationService.findAllByCity(city);
    }

    // Obtenir tous les centres de vaccination par code postal
    @GetMapping(path = "/postalCode/{postalCode}")
    public List<VaccinationCenter> getAllCenterByPostalCode(
        @PathVariable("postalCode") String postalCode) {
        return centreVaccinationService.findAllByPostalCode(postalCode);
    }

    // Obtenir un centre de vaccination par son nom
    @GetMapping(path = "/name/{name}")
    public Optional<VaccinationCenter> getCenterByName(
        @PathVariable("name") String name) {
        return centreVaccinationService.findByName(name);
    }

    // Lier un utilisateur à un centre de vaccination
    @PostMapping(path = "/utilisateur/add")
    public void linkUserToVaccinationCenter(
        @RequestParam("idUser") Long idUser,
        @RequestParam("nameCenter") String nameCenter) {
        centreVaccinationService.linkUserToVaccinationCenter(idUser,nameCenter);
    }

    // Obtenir tous les utilisateurs d'un centre de vaccination
    @GetMapping(path = "/utilisateur/{name}")
    public List<Users> getAllUsersByVaccinationCenter(
        @PathVariable("name") String name) {
        return centreVaccinationService.findAllUsersByVaccinationCenter(name);
    }

    
    // Supprimer un centre de vaccination par son nom s'il existe
    @DeleteMapping(path = "/delete/{name}")
    public Long deleteCenterByName(
        @PathVariable("name") String name) {
        return centreVaccinationService.deleteByName(name);
    }

    // Ajouter un centre de vaccination
    @PostMapping(path = "/add")
    public VaccinationCenter addCenter(@RequestBody VaccinationCenter centre){
        return centreVaccinationService.save(centre);
    }

    

    /* 
    // Ajouter un utilisateur à un centre de vaccination
    @GetMapping(path = "/utilisateur/add")
    public Optional<VaccinationCenter> addUserToCentreVaccination(
        @RequestParam("idCentre") Long idCentre,
        @RequestParam("idUser") Long idUser) {
        return centreVaccinationService.addUserToCentreVaccination(idCentre, idUser);
    }

    // Supprimer un utilisateur d'un centre de vaccination
    @GetMapping(path = "/utilisateur/delete")
    public Optional<VaccinationCenter> deleteUserFromCentreVaccination(
        @RequestParam("idCentre") Long idCentre,
        @RequestParam("idUser") Long idUser) {
        return centreVaccinationService.deleteUserFromCentreVaccination(idCentre, idUser);
    }

    // Obtenir tous les utilisateurs d'un centre de vaccination
    @GetMapping(path = "/utilisateur")
    public List<VaccinationCenter> findAllUsersFromCentreVaccination(
        @RequestParam("idCentre") Long idCentre) {
        return centreVaccinationService.findAllUsersFromCentreVaccination(idCentre);
    }

    // Obtenir tous les utilisateurs en fonction de leur role et d'un centre de vaccination
    @GetMapping(path = "/utilisateur")
    public List<VaccinationCenter> findAllUsersByRoleAndCentreVaccination(
        @RequestParam("role") String role,
        @RequestParam("idCentre") Long idCentre) {
        return centreVaccinationService.findAllUsersByRoleAndCentreVaccination(role, idCentre);
    }
 */


}