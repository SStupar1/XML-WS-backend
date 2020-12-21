package com.example.demo.repository;


import com.example.demo.entity.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFuelTypeRepository extends JpaRepository<FuelType, Long> {
    FuelType findOneById(Long id);
}
