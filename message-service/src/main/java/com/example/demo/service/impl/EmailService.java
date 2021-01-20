package com.example.demo.service.impl;


import com.example.demo.config.EmailContext;
import com.example.demo.dto.request.MailDTO;
import com.example.demo.service.IEmailService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements IEmailService {

    private final EmailContext _emailContext;

    public EmailService(EmailContext emailContext) {
        _emailContext = emailContext;
    }

    @Override
    public void receiveMessage(MailDTO mail){
        if(mail.getRole().equals("Agent")){
            agentRegistrationMail(mail);
        }else {
            approveRegistrationMail(mail);
        }
    }

    public void approveRegistrationMail(MailDTO simpleUser) {
        String to = simpleUser.getUsername();
        String subject = "Simple user registration announcement";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", simpleUser.getFirstName(), simpleUser.getLastName()));
        context.setVariable("link", String.format("http://localhost:4200/login/%s", simpleUser.getId()));
        _emailContext.send(to, subject, "approveRegistration", context);
        System.out.println("proslo slanje simple user maila");
    }

    public void agentRegistrationMail(MailDTO agent) {
        System.out.println(agent);
        String to = agent.getUsername();
        String subject = "Agent registration announcement";
        Context context = new Context();
        context.setVariable("name", String.format("%s", agent.getName()));
        _emailContext.send(to, subject, "agentRegistration", context);
        System.out.println("proslo slanje agent maila");
    }
}