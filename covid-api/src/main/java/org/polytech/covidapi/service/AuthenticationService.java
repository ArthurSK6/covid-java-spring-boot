package org.polytech.covidapi.service;

import org.polytech.covidapi.payload.request.AuthenticationRequest;
import org.polytech.covidapi.payload.request.RegisterRequest;
import org.polytech.covidapi.payload.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
