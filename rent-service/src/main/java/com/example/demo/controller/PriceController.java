package com.example.demo.controller;


import com.example.demo.services.IPriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
public class PriceController {

    private final IPriceService _priceService;

    public PriceController(IPriceService priceService) {
        _priceService = priceService;
    }

    @GetMapping("/starting-price")
    public ResponseEntity<?> calculateStartingPricePerAd(@RequestParam("priceCDW") double priceCDW,
                                                         @RequestParam("availableCDW") boolean availableCDW,
                                                         @RequestParam("pricePerDay") double pricePerDay,
                                                         @RequestParam("discount") double discount,
                                                         @RequestParam(value="fromDateString") String fromDateString,
                                                         @RequestParam(value="toDateString") String toDateString){
        return new ResponseEntity<>(_priceService.calculateStartingPricePerAd(priceCDW,availableCDW,pricePerDay,discount,fromDateString,toDateString), HttpStatus.OK);
    }
}
