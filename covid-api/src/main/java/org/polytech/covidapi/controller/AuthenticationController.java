package org.polytech.covidapi.controller;

import org.polytech.covidapi.domain.ERole;
import org.polytech.covidapi.payload.request.AuthenticationRequest;
import org.polytech.covidapi.payload.request.RefreshTokenRequest;
import org.polytech.covidapi.payload.request.RegisterRequest;
import org.polytech.covidapi.payload.response.AuthenticationResponse;
import org.polytech.covidapi.payload.response.RefreshTokenResponse;
import org.polytech.covidapi.service.AuthenticationService;
import org.polytech.covidapi.service.JwtService;
import org.polytech.covidapi.service.RefreshTokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Authentication", description = "The Authentication API. Contains operations like login, logout, refresh-token etc.")
@RestController
@RequestMapping("/api")
@SecurityRequirements() // Pas de sécurité pour ce module
@RequiredArgsConstructor
public class AuthenticationController {

        private final AuthenticationService authenticationService;
        private final RefreshTokenService refreshTokenService;
        private final AuthenticationManager authenticationManager;
        private final JwtService jwtService;

        // Définition variable URL superadmin
        private static final String URL_SUPERADMIN = "/superadmin/user";

        // Définition variable URL admin
        private static final String URL_ADMIN = "/admin/user";

        // Définition variable URL docteur
        private static final String URL_DOCTOR = "/doctor/user";

        // Définition variable URL public
          private static final String URL_PUBLIC = "/public/user";

        // Enregistrement d'un nouvel utilisateur quelconque
        @PostMapping(URL_SUPERADMIN + "/register")
        public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
                AuthenticationResponse authenticationResponse = authenticationService.register(request);
                return ResponseEntity.ok()
                        .body(authenticationResponse);
        }

        // Enregistrement d'un nouvel utilisateur docteur
        @PostMapping(URL_ADMIN + "/register")
        public ResponseEntity<AuthenticationResponse> registerDoctor(@Valid @RequestBody RegisterRequest request) {
                request.setRole(ERole.DOCTOR);
                AuthenticationResponse authenticationResponse = authenticationService.register(request);
                return ResponseEntity.ok()
                        .body(authenticationResponse);
        }

        // Authentification d'un utilisateur (login)
        @PostMapping(URL_PUBLIC + "/authenticate")
        @Operation(
                responses = {
                        @ApiResponse(
                                description = "Success",
                                responseCode = "200"
                        ),
                        @ApiResponse(
                                description = "Unauthorized",
                                responseCode = "401",
                                content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")}
                        )
                }
        )
        public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
                AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
                ResponseCookie jwtCookie = jwtService.generateJwtCookie(authenticationResponse.getAccessToken());
                ResponseCookie refreshTokenCookie = refreshTokenService.generateRefreshTokenCookie(authenticationResponse.getRefreshToken());
                return ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE,jwtCookie.toString())
                        .header(HttpHeaders.SET_COOKIE,refreshTokenCookie.toString())
                        .body(authenticationResponse);
        }

        // Génération d'un nouveau token JWT à partir d'un refresh-token
        @PostMapping(URL_PUBLIC + "/refresh-token")
        public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
                return ResponseEntity.ok(refreshTokenService.generateNewToken(request));
        }

        // Génération d'un nouveau token JWT à partir d'un refresh-token contenu dans un cookie
        @PostMapping(URL_PUBLIC + "/refresh-token-cookie")
        public ResponseEntity<Void> refreshTokenCookie(HttpServletRequest request) {
                String refreshToken = refreshTokenService.getRefreshTokenFromCookies(request);
                RefreshTokenResponse refreshTokenResponse = refreshTokenService
                        .generateNewToken(new RefreshTokenRequest(refreshToken));
                ResponseCookie NewJwtCookie = jwtService.generateJwtCookie(refreshTokenResponse.getAccessToken());
                return ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE, NewJwtCookie.toString())
                        .build();
        }

        // Récupération des informations de l'utilisateur
        @GetMapping(URL_DOCTOR + "/info")
        public Authentication getAuthentication(@RequestBody AuthenticationRequest request){
                return     authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        }

        // Déconnexion de l'utilisateur
        @PostMapping(URL_DOCTOR + "/logout")
        public ResponseEntity<Void> logout(HttpServletRequest request){
                String refreshToken = refreshTokenService.getRefreshTokenFromCookies(request);
                if(refreshToken != null) {
                refreshTokenService.deleteByToken(refreshToken);
                }
                ResponseCookie jwtCookie = jwtService.getCleanJwtCookie();
                ResponseCookie refreshTokenCookie = refreshTokenService.getCleanRefreshTokenCookie();
                return ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE,jwtCookie.toString())
                        .header(HttpHeaders.SET_COOKIE,refreshTokenCookie.toString())
                        .build();
        }
}
