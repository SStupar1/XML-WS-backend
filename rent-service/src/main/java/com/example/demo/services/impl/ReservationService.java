package com.example.demo.services.impl;

import com.example.demo.client.AdClient;
import com.example.demo.dto.client.Ad;
import com.example.demo.dto.request.ReservationRequest;
import com.example.demo.dto.response.ReservationResponse;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.IReservationRepository;
import com.example.demo.services.IReservationService;
import com.example.demo.util.enums.ReservationStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService implements IReservationService {

    private final IReservationRepository _reservationRepository;

    private final AdClient _adClient;


    public ReservationService(IReservationRepository reservationRepository, AdClient adClient) {
        _reservationRepository = reservationRepository;

        _adClient = adClient;
    }


    private List<ReservationResponse> mapReservationsToReservationResponses(List<Reservation> reservations){
        List<ReservationResponse> reservationResponses = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationResponse reservationResponse = mapReservationToReservationResponse(reservation);
            reservationResponses.add(reservationResponse);
        }
        return reservationResponses;
    }

    @Override
    public List<ReservationResponse> getAllAdReservations(Long id) {
        List<Reservation> reservations = new ArrayList<>();
        //prodji kroz sve rezervacije, izdvoj rezervacije odredjenog oglasa
        //proveri da li se ta rezervacija vec nalazi tu, ako ne
        //proveri da li je njen status  APPROVED
        //sada u listi imamo sve APPROVE-ovane rezervacije
        for(Reservation reservation: _reservationRepository.findAll()){
            if(reservation.getAdID().equals(id)){
                if(!reservations.contains(reservation)){
                    if(reservation.getStatus().equals(ReservationStatus.APPROVED)) {
                        reservations.add(reservation);
                    }
                }
            }
        }
        return mapReservationsToReservationResponses(reservations);
    }

    @Override
    public List<ReservationResponse> getAllUserReservations(Long id) {
        List<Reservation> reservations = new ArrayList<>();
        for(Reservation reservation: _reservationRepository.findAll()){
            if(reservation.getCustomerID().equals(id)){
                if(!reservations.contains(reservation)){
                    reservations.add(reservation);
                }
            }
        }
        return mapReservationsToReservationResponses(reservations);
    }

    @Override
    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setCustomerID(reservationRequest.getCustomerID());
        reservation.setFromDate(reservationRequest.getFromDate());
        reservation.setToDate(reservationRequest.getToDate());
        reservation.setFromTime(reservationRequest.getFromTime());
        reservation.setToTime(reservationRequest.getToTime());
        reservation.setStatus(ReservationStatus.PENDING);
        _reservationRepository.save(reservation);
        return mapReservationToReservationResponse(reservation);
    }

    private ReservationResponse mapReservationToReservationResponse(Reservation reservation) {
        ReservationResponse reservationResponse = new ReservationResponse();
        Ad adClientResponse = _adClient.getAd(reservation.getAdID());
        reservationResponse.setToDate(reservation.getToDate());
        reservationResponse.setFromDate(reservation.getFromDate());
        reservationResponse.setId(reservation.getId());
        reservationResponse.setFromTime(reservation.getFromTime());
        reservationResponse.setToTime(reservation.getToTime());
        reservationResponse.setAd(adClientResponse);
        return reservationResponse;
    }

    @Override
    public ReservationResponse approveReservation(Long id) {
        List<Reservation> reservations = _reservationRepository.findAll();
        Reservation reservation = _reservationRepository.findOneById(id);
        reservation.setStatus(ReservationStatus.APPROVED);
        for(Reservation r: reservations){
            if(reservation.getAdID().equals(r.getAdID())){
                if(reservation.getFromDate().isBefore(r.getFromDate())){
                    if(reservation.getToDate().isAfter(r.getFromDate())){
                        r.setStatus(ReservationStatus.DENIED);
                    }
                }else if(reservation.getFromDate().isAfter(r.getFromDate())){
                    if(reservation.getFromDate().isBefore(r.getToDate())){
                        r.setStatus(ReservationStatus.DENIED);
                    }
                }else{
                    if (reservation.getFromTime().isBefore(r.getFromTime())) {
                        if(reservation.getToTime().isAfter(r.getToTime())){
                            r.setStatus(ReservationStatus.DENIED);
                        }

                    }else if(reservation.getFromTime().isAfter(r.getFromTime())){
                        if(reservation.getFromTime().isBefore(r.getToTime())){
                            r.setStatus(ReservationStatus.DENIED);
                        }
                    }
                }
            }
        }
        return mapReservationToReservationResponse(reservation);
    }

    @Override
    public ReservationResponse denyReservation(Long id) {
        Reservation reservation = _reservationRepository.findOneById(id);
        reservation.setStatus(ReservationStatus.DENIED);
        return mapReservationToReservationResponse(reservation);
    }

}