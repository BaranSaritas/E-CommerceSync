package com.E_CommerceSync.E_CommerceSync.controller.mail;


import com.E_CommerceSync.E_CommerceSync.dto.request.mail.SendMailRequest;
import com.E_CommerceSync.E_CommerceSync.service.mail.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/mail")
@CrossOrigin("*")
public class MailController {

    private final MailService mailService;


    @PostMapping()
    public ResponseEntity<String> sendMail(@RequestBody SendMailRequest request) throws MessagingException {

        mailService.sendMail(request);

        return ResponseEntity.ok().build();
    }
}
