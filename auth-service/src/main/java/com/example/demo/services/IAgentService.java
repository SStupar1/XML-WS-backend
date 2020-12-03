package com.example.demo.services;

import com.example.demo.dto.request.UpdateAgentRequest;
import com.example.demo.dto.response.AgentResponse;

public interface IAgentService {

    AgentResponse getAgentById(Long id);

    void updateAgentById(Long id, UpdateAgentRequest request);

}
