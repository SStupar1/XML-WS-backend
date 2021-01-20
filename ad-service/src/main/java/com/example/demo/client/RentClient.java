package com.example.demo.client;

import com.example.demo.dto.client.Discount;
import com.example.demo.dto.client.Pricelist;
import com.example.demo.dto.client.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "rent-service")
public interface RentClient {

    @GetMapping("/pricelists/{id}")
    Pricelist getPricelist(@PathVariable("id") Long id);

    @GetMapping("/discounts/{id}")
    Discount getDiscount(@PathVariable("id") Long id);

    @GetMapping("/{id}")
    Reservation getReservation(@PathVariable("id") Long id);

    @GetMapping("/{id}/ad")
    ResponseEntity<?> getAllAdReservations(@PathVariable Long id);

    //rezervacije koje pripadaju useru(simple useru ili agentu)
    @GetMapping("/customer")
    ResponseEntity<?> getAllCustomerReservations(@RequestParam("customerId") Long customerId, @RequestParam("simpleUser") boolean simpleUser);
}
