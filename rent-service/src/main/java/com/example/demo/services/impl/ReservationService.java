package com.example.demo.services.impl;

import com.example.demo.client.AdClient;
import com.example.demo.dto.client.Ad;
import com.example.demo.dto.request.ReservationRequest;
import com.example.demo.dto.response.ReservationResponse;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.IBundleRepository;
import com.example.demo.repository.IReservationRepository;
import com.example.demo.services.IReservationService;
import com.example.demo.util.enums.ReservationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService implements IReservationService {

    private final IReservationRepository _reservationRepository;

    private final AdClient _adClient;

    private final IBundleRepository _bundleRepository;


    public ReservationService(IReservationRepository reservationRepository, AdClient adClient, IBundleRepository bundleRepository) {
        _reservationRepository = reservationRepository;
        _adClient = adClient;
        _bundleRepository = bundleRepository;
    }

    @Override
    public List<ReservationResponse> getAllAdReservations(Long id) {
        List<Reservation> reservations = new ArrayList<>();
        List<Reservation> allReservations = _reservationRepository.findAll();
        for(Reservation reservation: allReservations){
            if(reservation.getBundle() == null) {
                if (reservation.getAdId().equals(id)) {
                    if (reservation.getStatus().equals(ReservationStatus.PENDING)) {
                        reservations.add(reservation);
                    }
                }
            }
        }
        return mapReservationsToReservationResponses(reservations);
    }

    //pending rezervacije RADI TOP
    @Override
    public List<ReservationResponse> getAllPublisherReservations(Long publisherId, boolean simpleUser) {
        System.out.println(publisherId);
        System.out.println(simpleUser);
        List<Reservation> reservations = new ArrayList<>();
        List<Reservation> allReservations = _reservationRepository.findAllByStatus(ReservationStatus.PENDING);
        for(Reservation reservation: allReservations){
            //ako rezervacija ne pripada bundleu
            if(reservation.getBundle() == null) {
                Ad ad = _adClient.getAd(reservation.getAdId());
                //provera publisher-a
                if (ad.getPublisher().getId() == publisherId) {
                    if (simpleUser == ad.isSimpleUser()) {
                        reservations.add(reservation);
                    }
                }
            }
        }
        return mapReservationsToReservationResponses(reservations);
    }

    //vraca sve pojedinacne rezervacije od jednog customer-a RADI TOP
    @Override
    public List<ReservationResponse> getAllCustomerReservations(Long customerId, boolean simpleUser) {
        List<Reservation> reservations = new ArrayList<>();
        List<Reservation> allReservations = _reservationRepository.findAll();
        for(Reservation reservation: allReservations){
            if(reservation.getBundle() == null) {
                if (reservation.getCustomerId() == customerId) {
                    if (simpleUser == reservation.isSimpleUser()) {
                        reservations.add(reservation);
                    }
                }
            }
        }
        return mapReservationsToReservationResponses(reservations);
    }

    @Override
    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        Reservation reservation = createReservationEntity(reservationRequest);
        _reservationRepository.save(reservation);
        return mapReservationToReservationResponse(reservation);
    }

    @Override
    public ReservationResponse approveReservation(Long id) {
        List<Reservation> reservations = _reservationRepository.findAll();
        Reservation reservation = _reservationRepository.findOneById(id);
        reservation.setStatus(ReservationStatus.APPROVED);
        _reservationRepository.save(reservation);
        for(Reservation r: reservations){
            if(reservation.getAdId().equals(r.getAdId())){
                if(reservation.getFromDate().isBefore(r.getFromDate())){
                    if(reservation.getToDate().isAfter(r.getFromDate())){
                        r.setStatus(ReservationStatus.DENIED);
                        _reservationRepository.save(r);
                    }else if(reservation.getToTime().isAfter(r.getFromTime())){
                            r.setStatus(ReservationStatus.DENIED);
                            _reservationRepository.save(r);

                    }
                }else if(reservation.getFromDate().isAfter(r.getFromDate())){
                    if(reservation.getFromDate().isBefore(r.getToDate())){
                        r.setStatus(ReservationStatus.DENIED);
                        _reservationRepository.save(r);
                    }else if(r.getToTime().isAfter(reservation.getFromTime())){
                        r.setStatus(ReservationStatus.DENIED);
                        _reservationRepository.save(r);
                    }
                }else{
                    System.out.println("isti sam dan");
                    if (reservation.getFromTime().isBefore(r.getFromTime())) {
                        if(reservation.getToTime().isAfter(r.getToTime())){
                            r.setStatus(ReservationStatus.DENIED);
                            _reservationRepository.save(r);
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
        Reservation savedReservation = _reservationRepository.save(reservation);
        return mapReservationToReservationResponse(savedReservation);
    }

    public List<ReservationResponse> mapReservationsToReservationResponses(List<Reservation> reservations){
        List<ReservationResponse> reservationResponses = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationResponse reservationResponse = mapReservationToReservationResponse(reservation);
            reservationResponses.add(reservationResponse);
        }
        return reservationResponses;
    }

    public Reservation createReservationEntity(ReservationRequest reservationRequest) {
        LocalDate fromDate = LocalDate.parse(reservationRequest.getFromDateString());
        LocalTime fromTime = LocalTime.parse(reservationRequest.getFromTimeString());
        LocalDate toDate = LocalDate.parse(reservationRequest.getToDateString());
        LocalTime toTime = LocalTime.parse(reservationRequest.getToTimeString());
        Reservation reservation = new Reservation();
        reservation.setCustomerId(reservationRequest.getCustomerId());
        reservation.setAdId(reservationRequest.getAdId());
        reservation.setFromDate(fromDate);
        reservation.setToDate(toDate);
        reservation.setFromTime(fromTime);
        reservation.setToTime(toTime);
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setSimpleUser(reservationRequest.isSimpleUser());
        return reservation;
    }

    public ReservationResponse mapReservationToReservationResponse(Reservation reservation) {
        ReservationResponse reservationResponse = new ReservationResponse();
        Ad adClientResponse = _adClient.getAd(reservation.getAdId());
        reservationResponse.setToDate(reservation.getToDate());
        reservationResponse.setFromDate(reservation.getFromDate());
        reservationResponse.setId(reservation.getId());
        reservationResponse.setFromTime(reservation.getFromTime());
        reservationResponse.setToTime(reservation.getToTime());
        reservationResponse.setAd(adClientResponse);
        reservationResponse.setSimpleUser(reservation.isSimpleUser());
        reservationResponse.setStatus(reservation.getStatus());
        return reservationResponse;
    }


}