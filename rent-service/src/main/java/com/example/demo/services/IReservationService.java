package com.example.demo.services;

import com.example.demo.dto.request.CustomerReservationsRequest;
import com.example.demo.dto.request.RequestId;
import com.example.demo.dto.request.ReservationRequest;
import com.example.demo.dto.response.BundleResponse;
import com.example.demo.dto.response.ReservationResponse;

import java.util.List;

public interface IReservationService {
    List<ReservationResponse> getAllAdReservations(Long id);


    List<ReservationResponse> getAllCustomerReservations(CustomerReservationsRequest request);


    ReservationResponse createReservation(ReservationRequest reservationRequest);

    ReservationResponse approveReservation(Long id);

    ReservationResponse denyReservation(Long id);


    List<ReservationResponse> getAllPublisherReservations(CustomerReservationsRequest request);
}