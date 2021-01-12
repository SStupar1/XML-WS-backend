package com.example.demo.repository;

import com.example.demo.entity.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPricelistRepository extends JpaRepository<Pricelist, Long> {
    Pricelist findOneById(Long id);

}
