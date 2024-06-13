package com.E_CommerceSync.E_CommerceSync.service.mail;

public interface MailService {
    void reSendVerificationLink(String email);

    void verify(String email, String verifyCode);
}
