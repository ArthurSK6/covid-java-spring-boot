/* package org.polytech.covidapi;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.polytech.covidapi.domain.CentreVaccination;
import org.polytech.covidapi.domain.Utilisateurs;
import org.polytech.covidapi.repository.CentreVaccinationRepository;
import org.polytech.covidapi.repository.UtilisateursRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CovidApiApplicationTests {

	@Autowired
	private CentreVaccinationRepository centreVaccinationRepository;

	@Autowired
	private UtilisateursRepository utilisateursRepository;

	// On test l'ajout d'un centre de vaccination
	@Test
	void testAddCentreVaccination() {
		CentreVaccination centreVaccination = new CentreVaccination();
		centreVaccination.setName("Centre de vaccination de Nancy");
		centreVaccination.setCity("Nancy");
		centreVaccination.setPostalCode("54000");
		centreVaccination.setAddress("1 rue de la paix");
		centreVaccinationRepository.save(centreVaccination);
	}

	// On test la suppression d'un centre de vaccination
	@Test
	void testDeleteCentreVaccination() {
		CentreVaccination centreVaccination = new CentreVaccination();
		centreVaccination.setName("Centre de vaccination de Metz");
		centreVaccination.setCity("Metz");
		centreVaccination.setPostalCode("57000");
		centreVaccination.setAddress("4 rue jeanne d'arc");
		centreVaccinationRepository.save(centreVaccination);
		centreVaccinationRepository.deleteById(centreVaccination.getId());
	}

	// On test l'affichage de tous les centres de vaccination
	@Test
	void testGetAllCentreVaccination() {
		List<CentreVaccination> CentreVaccinations = centreVaccinationRepository.findAll();
		
		for (CentreVaccination centreVaccination : CentreVaccinations) {
			System.out.println(centreVaccination.getName());
		}
	}

	// On test l'ajout d'un utilisateur
	@Test
	void testAddUtilisateur() {
		Utilisateurs utilisateur = new Utilisateurs();
		utilisateur.setNom("Dupont");
		utilisateur.setPrenom("Jean");
		utilisateur.setEmail("jean.dupont@gmail.com");
		utilisateur.setPassword("123456");
		utilisateursRepository.save(utilisateur);
	}

	// On test la suppression d'un utilisateur
	@Test
	void testDeleteUtilisateur() {
		Utilisateurs utilisateur = new Utilisateurs();
		utilisateur.setNom("Martin");
		utilisateur.setPrenom("Paul");
		utilisateur.setEmail("paul.martin@gmail.com");
		utilisateur.setPassword("123456");
		utilisateursRepository.save(utilisateur);
		utilisateursRepository.deleteById(utilisateur.getId());
	}

	// On test l'affichage de tous les utilisateurs
	@Test
	void testGetAllUtilisateur() {
		List<Utilisateurs> utilisateurs = utilisateursRepository.findAll();
		
		for (Utilisateurs utilisateur : utilisateurs) {
			System.oust.println(utilisateur.getNom());
		}
	}
} */