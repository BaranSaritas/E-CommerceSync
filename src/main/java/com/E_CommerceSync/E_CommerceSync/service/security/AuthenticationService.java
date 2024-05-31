package com.E_CommerceSync.E_CommerceSync.service.security;


import com.E_CommerceSync.E_CommerceSync.dto.security.request.SignUpRequest;
import com.E_CommerceSync.E_CommerceSync.dto.security.request.SigninRequest;
import com.E_CommerceSync.E_CommerceSync.dto.security.res.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
