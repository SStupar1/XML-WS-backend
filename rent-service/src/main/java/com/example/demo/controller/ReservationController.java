package com.example.demo.controller;

import com.example.demo.dto.request.RequestId;
import com.example.demo.dto.request.ReservationRequest;
import com.example.demo.dto.response.PricelistResponse;
import com.example.demo.dto.response.ReportResponse;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservation(@PathVariable("id") Long id){
        ReservationResponse reservationResponse = _reservationService.getReservation(id);
        if(reservationResponse != null){
            return new ResponseEntity<>(reservationResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Reservation doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/generate-report")
    public ResponseEntity<?> generateReport(@RequestParam("reservationId") Long reservationId, @RequestParam("kmTraveled") double kmTraveled ){
        ReportResponse reportResponse = _reservationService.generateReport(reservationId, kmTraveled);
        if(reportResponse != null){
            return new ResponseEntity<>(reportResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Report doesn't exist.", HttpStatus.NOT_FOUND);
        }
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

    //sve rezervacije koje je customer porucio koje ne pripadaju bundle paketu
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

    //sve publisherove rezervacije sa statusom PENDING koje ne pripadaju bundle paketu
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

    @GetMapping("/publisher/approved")
    public ResponseEntity<?> getAllApprovedPublisherReservations(@RequestParam("publisherId") Long publisherId, @RequestParam("simpleUser") boolean simpleUser){
        List<ReservationResponse> retVal = _reservationService.getAllApprovedPublisherReservations(publisherId, simpleUser);
        if(retVal.isEmpty()){
            StringResponse stringResponse = new StringResponse();
            stringResponse.setText("There is no approved reservations.");
            return new ResponseEntity<>(stringResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    //kreiranje rezervacije od strane customera
    @PostMapping()
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest reservationRequest){
        return new ResponseEntity<>(_reservationService.createReservation(reservationRequest), HttpStatus.OK);
    }

    //kreiranje rezervacije kada lice fizicki rentira vozilo od strane agenta
    @PostMapping("/agent-create")
    public ResponseEntity<?> createReservationByAgent(@RequestBody ReservationRequest request){
        return new ResponseEntity<>(_reservationService.createReservationByAgent(request), HttpStatus.OK);
    }

    @PutMapping("/approve")
        public ResponseEntity<?> approveReservation(@RequestBody RequestId request){
        return new ResponseEntity<>(_reservationService.approveReservation(request.getId()), HttpStatus.OK);
    }

    @PutMapping("/deny")
    public ResponseEntity<?> denyReservation(@RequestBody RequestId request){
        return new ResponseEntity<>(_reservationService.denyReservation(request.getId()), HttpStatus.OK);
    }
}
