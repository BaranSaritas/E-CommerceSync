package com.E_CommerceSync.E_CommerceSync.service.security;

import com.E_CommerceSync.E_CommerceSync.model.security.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    boolean isUserVerified(String email);
    void verifyUser(String email);
    User getUserByEmail(String email);
}
