package org.polytech.covidapi.service;

import java.util.Optional;

import org.polytech.covidapi.domain.RefreshToken;
import org.polytech.covidapi.payload.request.RefreshTokenRequest;
import org.polytech.covidapi.payload.response.RefreshTokenResponse;
import org.springframework.http.ResponseCookie;

import jakarta.servlet.http.HttpServletRequest;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(Long userId);
    RefreshToken verifyExpiration(RefreshToken token);
    Optional<RefreshToken> findByToken(String token);
    RefreshTokenResponse generateNewToken(RefreshTokenRequest request);
    ResponseCookie generateRefreshTokenCookie(String token);
    String getRefreshTokenFromCookies(HttpServletRequest request);
    void deleteByToken(String token);
    ResponseCookie getCleanRefreshTokenCookie();
}
