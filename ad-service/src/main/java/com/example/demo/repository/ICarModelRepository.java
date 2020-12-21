package com.example.demo.repository;

import com.example.demo.entity.CarBrand;
import com.example.demo.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarModelRepository extends JpaRepository<CarModel, Long> {
    CarModel findOneById(Long id);
}
