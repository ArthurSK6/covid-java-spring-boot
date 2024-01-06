package org.polytech.covidapi.service.impl;

import org.polytech.covidapi.domain.TokenType;
import org.polytech.covidapi.domain.Users;
import org.polytech.covidapi.payload.request.AuthenticationRequest;
import org.polytech.covidapi.payload.request.RegisterRequest;
import org.polytech.covidapi.payload.response.AuthenticationResponse;
import org.polytech.covidapi.repository.UsersRepository;
import org.polytech.covidapi.service.AuthenticationService;
import org.polytech.covidapi.service.JwtService;
import org.polytech.covidapi.service.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;



@Service @Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final UsersRepository userRepository;
        private final AuthenticationManager authenticationManager;
        private final RefreshTokenService refreshTokenService;
        
        // Enregistrement d'un nouvel utilisateur
        @Override
        public AuthenticationResponse register(RegisterRequest request) {
                var user = Users.builder()
                        .prenom(request.getPrenom())
                        .nom(request.getNom())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(request.getRole())
                        .vaccinationCenter(request.getVaccinationCenter())
                        .build();
                user = userRepository.save(user);
                var jwt = jwtService.generateToken(user);
                var refreshToken = refreshTokenService.createRefreshToken(user.getId());

                var roles = user.getRole().getAuthorities()
                        .stream()
                        .map(SimpleGrantedAuthority::getAuthority)
                        .toList();

                return AuthenticationResponse.builder()
                        .accessToken(jwt)
                        .email(user.getEmail())
                        .id(user.getId())
                        .refreshToken(refreshToken.getToken())
                        .roles(roles)
                        .tokenType( TokenType.BEARER.name())
                        .build();
        }

        // Authentification d'un utilisateur (login)
        @Override
        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
                );

                var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Email ou mot de passe invalide."));
                var roles = user.getRole().getAuthorities()
                        .stream()
                        .map(SimpleGrantedAuthority::getAuthority)
                        .toList();
                var jwt = jwtService.generateToken(user);
                var refreshToken = refreshTokenService.createRefreshToken(user.getId());
                return AuthenticationResponse.builder()
                        .accessToken(jwt)
                        .roles(roles)
                        .email(user.getEmail())
                        .id(user.getId())
                        .refreshToken(refreshToken.getToken())
                        .tokenType( TokenType.BEARER.name())
                        .build();
        }
}
