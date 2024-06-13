package com.E_CommerceSync.E_CommerceSync.service.mail;

import com.E_CommerceSync.E_CommerceSync.dto.request.mail.SendMailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {


    @Value("${spring.mail.username}")
    private String email;


    @Autowired
    private  JavaMailSender mailSender;

    public void sendMail(SendMailRequest sendMailRequest) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(sendMailRequest.getRecipientEmail());

        message.setSubject(sendMailRequest.getSubject());
        message.setText(sendMailRequest.getContent());

        mailSender.send(message);

    }



}
