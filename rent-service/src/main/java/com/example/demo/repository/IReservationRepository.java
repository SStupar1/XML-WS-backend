package com.example.demo.repository;

import com.example.demo.entity.Reservation;
import com.example.demo.util.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findOneById(Long id);

    List<Reservation> findAllByStatus(ReservationStatus pending);
}

