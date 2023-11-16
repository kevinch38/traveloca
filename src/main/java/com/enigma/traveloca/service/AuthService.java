package com.enigma.traveloca.service;

import com.enigma.traveloca.dto.request.AuthRequest;
import com.enigma.traveloca.dto.response.LoginResponse;
import com.enigma.traveloca.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(AuthRequest request);
    RegisterResponse registerAdmin(AuthRequest request);
    LoginResponse login(AuthRequest request);
}

