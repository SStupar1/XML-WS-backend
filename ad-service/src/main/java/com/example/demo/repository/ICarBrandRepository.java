package com.example.demo.repository;

import com.example.demo.entity.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarBrandRepository extends JpaRepository<CarBrand, Long> {
    CarBrand findOneById(Long id);

}
