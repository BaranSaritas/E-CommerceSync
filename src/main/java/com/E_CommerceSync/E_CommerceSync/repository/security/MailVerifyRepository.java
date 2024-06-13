package com.E_CommerceSync.E_CommerceSync.repository.security;

import com.E_CommerceSync.E_CommerceSync.model.user.MailVerify;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MailVerifyRepository extends JpaRepository<MailVerify, Long> {

    List<MailVerify> findByEmail(String email);

    Optional<MailVerify> findByEmailAndVerifyCode(String email, String verifyCode);

    Optional<MailVerify> findByEmailAndVerifyCodeAndIsExpiredFalse(String email, String verifyCode);


}
