package com.example.demo.service;

import com.example.demo.dto.request.MailDTO;

public interface IEmailService {

    void receiveMessage(MailDTO mail);
}
