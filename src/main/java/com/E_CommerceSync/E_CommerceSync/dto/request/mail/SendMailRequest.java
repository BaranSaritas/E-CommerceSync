package com.E_CommerceSync.E_CommerceSync.dto.request.mail;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendMailRequest {

    private String recipientEmail;
    private String subject;
    private String content;

}



/*

String recipientEmail = "recipient@example.com";
String subject = "Hello from Spring Boot";
String content = "<p>Hello,</p><p>This is a test email sent from Spring Boot.</p>";

 */