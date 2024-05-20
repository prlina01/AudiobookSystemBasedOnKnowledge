package com.example.sbnzproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {


    private JavaMailSender emailSender;

    @Async
    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("willsetup...");
        message.setTo("tempMail@hotmail.com");
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }
}