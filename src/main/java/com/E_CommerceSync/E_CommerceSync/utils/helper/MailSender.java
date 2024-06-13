package com.E_CommerceSync.E_CommerceSync.utils.helper;

import com.E_CommerceSync.E_CommerceSync.exception.BusinessException;
import com.E_CommerceSync.E_CommerceSync.exception.reasons.BusinessExceptionReason;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MailSender {

    @Value("${spring.mail.from}")
    private String from;

    private final JavaMailSender emailSender;
    @Autowired
    private final SpringTemplateEngine templateEngine;

    

    private void sendHtmlMessage(ThymeLeafEmail email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(email.getProperties());
        helper.setFrom(email.getFrom());
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        String html = templateEngine.process(email.getTemplate(), context);
        helper.setText(html, true);
        emailSender.send(message);
    }

    public void sendVerificationMail(String email, String subject, String template, String name, String token){
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("verifyCode", token);
        properties.put("email", email);

        if(Objects.isNull(from)){
            throw new BusinessException(BusinessExceptionReason.FROM_MAIL_IS_NULL);
        }

        ThymeLeafEmail email1 = ThymeLeafEmail.builder()
                .to(email)
                .from(from)
                .subject(subject)
                .template(template)
                .properties(properties)
                .build();

        try {
            sendHtmlMessage(email1);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
