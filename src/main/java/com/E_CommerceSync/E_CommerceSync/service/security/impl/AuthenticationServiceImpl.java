package com.E_CommerceSync.E_CommerceSync.service.security.impl;

import com.E_CommerceSync.E_CommerceSync.dto.security.request.SignUpRequest;
import com.E_CommerceSync.E_CommerceSync.dto.security.request.SigninRequest;
import com.E_CommerceSync.E_CommerceSync.dto.security.res.JwtAuthenticationResponse;
import com.E_CommerceSync.E_CommerceSync.exception.BusinessException;
import com.E_CommerceSync.E_CommerceSync.exception.reasons.BusinessExceptionReason;
import com.E_CommerceSync.E_CommerceSync.model.security.Role;
import com.E_CommerceSync.E_CommerceSync.model.security.User;
import com.E_CommerceSync.E_CommerceSync.model.user.MailVerify;
import com.E_CommerceSync.E_CommerceSync.repository.security.MailVerifyRepository;
import com.E_CommerceSync.E_CommerceSync.repository.security.UserRepository;
import com.E_CommerceSync.E_CommerceSync.service.security.AuthenticationService;
import com.E_CommerceSync.E_CommerceSync.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MailVerifyRepository mailVerifyRepository;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {

        checkIfEmailExists(request.getEmail());

        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);

        MailVerify mailVerify = MailVerify.builder()
                .email(user.getEmail())
                .build();

        mailVerifyRepository.save(mailVerify);


        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    private void checkIfEmailExists(String email) {
       if( userRepository.existsByEmail(email)){
           throw new BusinessException(BusinessExceptionReason.EMAIL_ALREADY_EXISTS);

       }
    }

}
