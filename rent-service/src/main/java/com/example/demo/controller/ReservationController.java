package com.example.demo.controller;

import com.example.demo.dto.request.ReservationRequest;
import com.example.demo.dto.response.ReservationResponse;
import com.example.demo.entity.Reservation;
import com.example.demo.services.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {
    public final IReservationService _reservationService;

    public ReservationController(IReservationService reservationService) {
        _reservationService = reservationService;
    }


    //sve rezervacije koje pripadaju jednom autu/oglasu
    @GetMapping("/{id}/car")
    public List<ReservationResponse> getAllAdReservations(@PathVariable Long id) {
        return _reservationService.getAllAdReservations(id);
    }

    //rezervacije koje pripadaju useru(simple useru ili agentu)
    @GetMapping("{id}/user")
    public List<ReservationResponse> getAllUserReservations(@PathVariable Long id){
        return _reservationService.getAllUserReservations(id);
    }

    @PostMapping()
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest reservationRequest){
        return new ResponseEntity<>(_reservationService.createReservation(reservationRequest), HttpStatus.OK);
    }

    @PutMapping("approve/{id}/reservation")
    public ResponseEntity<?> approveReservation(@PathVariable Long id){
        return new ResponseEntity<>(_reservationService.approveReservation(id), HttpStatus.OK);

    }

    @PutMapping("deny/{id}/reservation")
    public ResponseEntity<?> denyReservation(@PathVariable Long id){
        return new ResponseEntity<>(_reservationService.denyReservation(id), HttpStatus.OK);
    }



}
