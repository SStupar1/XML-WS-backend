package com.example.demo.util.rabbit;

import com.example.demo.dto.request.MailDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer {
    @Value("${fanout.exchange}")
    private String fanoutExchange;
    private final RabbitTemplate rabbitTemplate;
    @Autowired
    public QueueProducer(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }
    public void produce(MailDTO mailDTO) throws Exception {
        rabbitTemplate.setExchange(fanoutExchange);
        rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(mailDTO));
    }
}
