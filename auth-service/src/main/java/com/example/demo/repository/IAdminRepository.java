package com.example.demo.repository;


import com.example.demo.entity.Admin;
import com.example.demo.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, Long> {

    Admin findOneById(Long id);
}
