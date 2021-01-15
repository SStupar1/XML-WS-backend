package com.example.demo.repository;

import com.example.demo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByCar_Id(Long id);

    List<Reservation> findAllBySimpleUser_Id(Long id);

    Reservation findOneById(Long id);
}

