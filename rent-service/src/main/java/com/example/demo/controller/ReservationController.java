package com.example.demo.controller;

import com.example.demo.dto.request.ReservationRequest;
import com.example.demo.dto.response.ReservationResponse;
import com.example.demo.dto.response.StringResponse;
import com.example.demo.services.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    public final IReservationService _reservationService;

    public ReservationController(IReservationService reservationService) {
        _reservationService = reservationService;
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        System.out.println("Hello from rent service");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //sve rezervacije koje pripadaju jednom autu/oglasu koje su na PENDING-u
    @GetMapping("/{id}/ad")
    public ResponseEntity<?> getAllAdReservations(@PathVariable Long id) {
        List<ReservationResponse> retVal = _reservationService.getAllAdReservations(id);
        if(retVal.isEmpty()){
            StringResponse stringResponse = new StringResponse();
            stringResponse.setText("There is no reservations for that advertisement.");
            return new ResponseEntity<>(stringResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    //rezervacije koje pripadaju useru(simple useru ili agentu)
    @GetMapping("/customer")
    public ResponseEntity<?> getAllCustomerReservations(@RequestParam("customerId") Long customerId, @RequestParam("simpleUser") boolean simpleUser){
        List<ReservationResponse> retVal = _reservationService.getAllCustomerReservations(customerId, simpleUser);
        if(retVal.isEmpty()){
            StringResponse stringResponse = new StringResponse();
            stringResponse.setText("You did not reserve any car yet.");
            return new ResponseEntity<>(stringResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping("/publisher")
    public ResponseEntity<?> getAllPublisherReservations(@RequestParam("publisherId") Long publisherId, @RequestParam("simpleUser") boolean simpleUser){
        List<ReservationResponse> retVal = _reservationService.getAllPublisherReservations(publisherId, simpleUser);
        if(retVal.isEmpty()){
            StringResponse stringResponse = new StringResponse();
            stringResponse.setText("There is no reservations.");
            return new ResponseEntity<>(stringResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest reservationRequest){
        return new ResponseEntity<>(_reservationService.createReservation(reservationRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approveReservation(@PathVariable Long id){
        return new ResponseEntity<>(_reservationService.approveReservation(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/deny")
    public ResponseEntity<?> denyReservation(@PathVariable Long id){
        return new ResponseEntity<>(_reservationService.denyReservation(id), HttpStatus.OK);
    }
}
