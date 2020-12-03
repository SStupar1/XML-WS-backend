package com.example.demo.controller;

import com.example.demo.dto.response.AgentResponse;
import com.example.demo.services.IAgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentController {

    private final IAgentService _agentService;

    public AgentController(IAgentService agentService) {
        _agentService = agentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAgent(@PathVariable("id") Long id){
        AgentResponse agentResponse = _agentService.getAgentById(id);
        if(agentResponse != null){
            return new ResponseEntity<>(agentResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Agent doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
    

}