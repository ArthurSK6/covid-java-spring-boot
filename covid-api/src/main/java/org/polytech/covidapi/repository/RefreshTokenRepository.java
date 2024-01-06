package org.polytech.covidapi.repository;

import java.util.Optional;

import org.polytech.covidapi.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    // Supprimer le token de rafraichissement par l'id de son utilisateur
    void deleteByUserId(Long userId);
}
