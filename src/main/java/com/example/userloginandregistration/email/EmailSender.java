package com.example.userloginandregistration.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public interface EmailSender {


    public void send(String to, String email);
}
