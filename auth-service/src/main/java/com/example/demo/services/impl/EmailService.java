package com.example.demo.services.impl;

import com.example.demo.config.EmailContext;
import com.example.demo.entity.SimpleUser;
import com.example.demo.services.IEmailService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements IEmailService {

    private final EmailContext _emailContext;

    public EmailService(EmailContext emailContext) {
        _emailContext = emailContext;
    }

    @Override
    public void approveRegistrationMail(SimpleUser simpleUser) {
        String to = simpleUser.getUser().getUsername();
        System.out.println(to);
        String subject = "Your registration has been approved.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", simpleUser.getFirstName(), simpleUser.getLastName()));
        context.setVariable("link", String.format("http://localhost:4200/login/%s", simpleUser.getId()));
        _emailContext.send(to, subject, "approvedRegistration", context);
    }
}
