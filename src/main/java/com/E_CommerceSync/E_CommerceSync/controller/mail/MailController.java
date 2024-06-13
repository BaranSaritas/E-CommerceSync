package com.E_CommerceSync.E_CommerceSync.controller.mail;

import com.E_CommerceSync.E_CommerceSync.service.mail.MailService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/reSendVerificationLink/{email}")
    public ResponseEntity<Void> reSendVerificationLink(@PathVariable("email") String email) {
        mailService.reSendVerificationLink(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verify")
    public String verify(@NonNull @RequestParam("email") String email, @NonNull @RequestParam("verifyCode") String verifyCode) throws IOException {
        return """
                 <!DOCTYPE html>
                <html>
                <head>
                  <title>Email Verification Success</title>
                  <style>
                        html{
                            font-size: 16px;
                            background-color: #f9f9f9;
                            font-family: Arial, sans-serif;
                        }

                        .container{
                            width: 100%;
                            max-width: 600px;
                            margin: 0 auto;
                            padding: 20px;
                            box-sizing: border-box;
                            display: flex;
                            flex-direction: column;
                            justify-content: center;
                            align-items: center;
                        }
                        .logo{
                            background-color: red;
                            padding: 20px;
                            border-radius: 16px;
                        }
                        .content{
                            display: flex;
                            flex-direction: column;
                            justify-content: center;
                            align-items: center;
                            background-color: white;
                            padding: 20px;
                            border-radius: 16px;
                            margin-top: 20px;
                            box-shadow: 0 0 10px rgba(0,0,0,0.1);
                        }
                        .content h1{
                            margin: 0;
                            padding: 0;
                            font-size: 24px;
                            font-weight: 600;
                            color: #5B9F77;
                            margin-bottom: 30px;
                        }
                        .content p{

                            margin: 0;
                            padding: 0;
                            font-size: 16px;
                            color: #333;
                        }
                        .content p.success-message{
                            margin-top:30px;
                            font-size: 18px;
                            font-weight: 600;
                            color: black;
                            margin-bottom: 20px;
                        }
                        .content p.success-description{
                            text-align: center;
                            font-size: 14px;
                            color: #333;
                            margin-bottom: 20px;
                        }
                        .content a.login-button{
                            text-decoration: none;
                            background-color: #5B9F77;
                            color: white;
                            padding: 10px 20px;
                            border-radius: 8px;
                            font-size: 14px;
                            font-weight: 600;
                            margin-bottom: 20px;
                        }
                        .footer{
                            margin-top: 20px;
                            text-align: center;
                        }
                        .footer p{
                            margin: 0;
                            padding: 0;
                            font-size: 12px;
                            color: #333;
                        }
                    </style>
                </head>
                <body>
                <div class="container">
                  <div class="logo">Logo</div>
                  <div class="content">
                    <h1>Account Activated</h1>
                    <img  src="https://cdn-icons-png.flaticon.com/128/5397/5397335.png" alt="undraw-confirmed-81ex" border="0">
                    <p class="success-message">Hello</p>
                    <p class="success-description">Thank you, your email has been verified. Your account is now active. Please use the link below to login your account.</p>
                    <a class="login-button" href="https://www.test.mahalgo.com/auth/login " >Login Now</a>
                  </div>
                  <div class="footer">
                    <p>2023 State Go <em>All Rights Reserved</em></p>
                  </div>
                </div>
                </body>
                </html>
                                """;

    }
}
