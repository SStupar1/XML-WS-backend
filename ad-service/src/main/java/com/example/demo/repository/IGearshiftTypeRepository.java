package com.example.demo.repository;

import com.example.demo.entity.GearshiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGearshiftTypeRepository  extends JpaRepository<GearshiftType, Long> {
    GearshiftType findOneById(Long id);
}
