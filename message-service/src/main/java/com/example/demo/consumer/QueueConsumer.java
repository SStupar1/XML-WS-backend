package com.example.demo.consumer;


import com.example.demo.dto.request.MailDTO;
import com.example.demo.service.impl.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @Autowired
    EmailService emailService;

    public void receiveMessage(String message) {
        processMessage(message);
    }

    public void receiveMessage(byte[] message) {
        String strMessage = new String(message);
        processMessage(strMessage);
    }
    private void processMessage(String message) {
        try {
            MailDTO mail = new ObjectMapper().readValue(message, MailDTO.class);
            emailService.receiveMessage(mail);
        } catch(Exception e) {
            System.out.println("Mail not sent");
        }
    }
}
