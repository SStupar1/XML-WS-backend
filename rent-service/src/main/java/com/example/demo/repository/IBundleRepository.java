package com.example.demo.repository;

import com.example.demo.entity.Bundle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBundleRepository extends JpaRepository<Bundle, Long> {
}
