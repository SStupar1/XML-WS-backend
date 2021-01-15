package com.example.demo.controller;

import com.example.demo.dto.request.CustomerReservationsRequest;
import com.example.demo.dto.request.RequestId;
import com.example.demo.dto.request.ReservationRequest;
import com.example.demo.dto.response.BundleResponse;
import com.example.demo.dto.response.ReservationResponse;
import com.example.demo.dto.response.StringResponse;
import com.example.demo.entity.Reservation;
import com.example.demo.services.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //sve rezervacije koje pripadaju jednom autu/oglasu
    @GetMapping("/{id}/ad")
    public List<ReservationResponse> getAllAdReservations(@PathVariable Long id) {
        return _reservationService.getAllAdReservations(id);
    }

    //rezervacije koje pripadaju useru(simple useru ili agentu)
    @GetMapping("/customer")
    public List<ReservationResponse> getAllCustomerReservations(@RequestBody CustomerReservationsRequest request){
        return _reservationService.getAllCustomerReservations(request);
    }

    @GetMapping("/publisher")
    public List<ReservationResponse> getAllPublisherReservations(@RequestBody CustomerReservationsRequest request){
        return _reservationService.getAllPublisherReservations(request);
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
