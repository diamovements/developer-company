package com.example.developer.services;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;
    public void sendEmail(String toEmail, String subject, String body, String path) throws Exception {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText("<html><body><p>" + body + "</p><img src='cid:image' /></body></html>", true);
            ClassPathResource image = new ClassPathResource(path);
            helper.addInline("image", image);

            javaMailSender.send(message);
        }
        catch (Exception e) {
            throw new Exception("Error while sending email", e);
        }
    }
}