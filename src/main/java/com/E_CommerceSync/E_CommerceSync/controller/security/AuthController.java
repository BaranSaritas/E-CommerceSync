package com.E_CommerceSync.E_CommerceSync.controller.security;


import com.E_CommerceSync.E_CommerceSync.dto.security.request.SignUpRequest;
import com.E_CommerceSync.E_CommerceSync.dto.security.request.SigninRequest;
import com.E_CommerceSync.E_CommerceSync.dto.security.res.JwtAuthenticationResponse;
import com.E_CommerceSync.E_CommerceSync.service.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationService service;

    @PostMapping()
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request){
        service.signup(request);
        // sms sistemi gelistirilecek
        return ResponseEntity.ok("Kaydiniz yapildi mail onayi bekliyor");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SigninRequest request){
        return ResponseEntity.ok(service.signin(request));
    }

}
