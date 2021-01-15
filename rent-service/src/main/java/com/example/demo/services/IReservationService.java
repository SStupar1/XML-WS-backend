package com.example.demo.services;

import com.example.demo.dto.request.ReservationRequest;
import com.example.demo.dto.response.ReservationResponse;
import com.example.demo.entity.Reservation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IReservationService {
    List<ReservationResponse> getAllAdReservations(Long id);


    List<ReservationResponse> getAllUserReservations(Long id);


    ReservationResponse createReservation(ReservationRequest reservationRequest);

    ReservationResponse approveReservation(Long id);

    ReservationResponse denyReservation(Long id);
}