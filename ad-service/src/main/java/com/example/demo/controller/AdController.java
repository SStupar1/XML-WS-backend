package com.example.demo.controller;

import com.example.demo.dto.response.AdResponse;
import com.example.demo.services.IAdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("ads")
public class AdController {

    private final IAdService _adService;

    public AdController(IAdService adService) {
        _adService = adService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAd(@PathVariable("id") Long id){

        AdResponse adResponse = _adService.getAdById(id);
        if(adResponse != null){
            return new ResponseEntity<>(adResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Ad doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }


}
