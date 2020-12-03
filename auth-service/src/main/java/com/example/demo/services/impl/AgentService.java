package com.example.demo.services.impl;

import com.example.demo.dto.response.AgentResponse;
import com.example.demo.entity.Agent;
import com.example.demo.repository.IAgentRepository;
import com.example.demo.services.IAgentService;
import org.springframework.stereotype.Service;

@Service
public class AgentService implements IAgentService {

    private final IAgentRepository _agentRepository;

    public AgentService(IAgentRepository agentRepository) {
        _agentRepository = agentRepository;
    }

    @Override
    public AgentResponse getAgentById(Long id) {
        Agent agent = _agentRepository.findOneById(id);
        if(agent != null){
            return mapAgentToResponse(agent);
        }
        else{
            return null;
        }
    }

    private AgentResponse mapAgentToResponse(Agent agent){
        AgentResponse agentResponse = new AgentResponse();
        agentResponse.setId(agent.getId());
        agentResponse.setUsername(agent.getUser().getUsername());
        agentResponse.setUserRole(agent.getUser().getUserRole().toString());
        agentResponse.setName(agent.getName());
        agentResponse.setPib(agent.getPib());
        agentResponse.setBankAccountNumber(agent.getBankAccountNumber());
        agentResponse.setAddress(agent.getAddress());
        agentResponse.setDateFounded(agent.getDateFounded());
        agentResponse.setSimpleUserId(agent.getSimpleUserId());

        return agentResponse;
    }
}
