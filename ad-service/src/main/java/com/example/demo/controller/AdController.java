package com.example.demo.controller;

import com.example.demo.dto.request.CreateAdRequest;
import com.example.demo.dto.request.CreateCarRequest;
import com.example.demo.dto.request.UpdateAdRequest;
import com.example.demo.dto.request.UpdateCarModelRequest;
import com.example.demo.dto.response.AdResponse;
import com.example.demo.dto.response.CarResponse;
import com.example.demo.dto.response.TextResponse;
import com.example.demo.services.IAdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("ads")
public class AdController {

    private final IAdService _adService;

    public AdController(IAdService adService) {
        _adService = adService;
    }

    @GetMapping()
    public List<AdResponse> getAllAds(){
        return _adService.getAllAds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAd(@PathVariable("id") Long id){

        AdResponse adResponse = _adService.getAdById(id);
        if(adResponse != null){
            return new ResponseEntity<>(adResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Advertisement doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAd(@PathVariable("id") Long id, @RequestBody UpdateAdRequest request){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Updated !");
        if(_adService.updateAdById(id, request)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Advertisement doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable("id") Long id){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Deleted !");
        if(_adService.deleteAdById(id)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Advertisement doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping()
    public AdResponse createAd(@RequestBody CreateAdRequest request){
        return _adService.createAd(request);
    }

}
