package com.example.demo.repository;

import com.example.demo.entity.Bundle;
import com.example.demo.util.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBundleRepository extends JpaRepository<Bundle, Long> {
    List<Bundle> findAllByStatus(ReservationStatus pending);

    Bundle findOneById(Long id);
}
