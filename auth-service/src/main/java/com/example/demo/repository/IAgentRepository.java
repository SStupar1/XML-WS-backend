package com.example.demo.repository;

import com.example.demo.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgentRepository extends JpaRepository<Agent, Long> {

    Agent findOneById(Long id);

}
