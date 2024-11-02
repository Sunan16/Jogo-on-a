package com.jogonca.api_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordRecoveryEmail(String to, String linkConfirm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Recuperação de Senha");
        message.setText(String.format("Clique no link e confirme automaticamente a alteração de senha: %s", linkConfirm));
        mailSender.send(message);
    }

}
