package com.E_CommerceSync.E_CommerceSync.service.security.impl;

import com.E_CommerceSync.E_CommerceSync.exception.BusinessException;
import com.E_CommerceSync.E_CommerceSync.exception.reasons.BusinessExceptionReason;
import com.E_CommerceSync.E_CommerceSync.model.security.User;
import com.E_CommerceSync.E_CommerceSync.repository.security.UserRepository;
import com.E_CommerceSync.E_CommerceSync.service.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
    @Override
    public boolean isUserVerified(String email) {
        User user = findByEmail(email);
        return user.isVerified();
    }
    private void checkEmailExists(String email) {
        boolean result = userRepository.existsByEmailIgnoreCase(email);
        if(result)
            throw new BusinessException(BusinessExceptionReason.EMAIL_ALREADY_EXISTS, email);
    }

    @Override
    public void verifyUser(String email) {
        User user = findByEmail(email);
        user.setVerified(true);
        userRepository.save(user);
    }
    @Override
    public User getUserByEmail(String email) {
        return findByEmail(email);
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(BusinessExceptionReason.USER_NOT_FOUND_BY_EMAIL));
    }
}
