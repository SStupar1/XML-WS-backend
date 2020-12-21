package com.example.demo.controller;

import com.example.demo.dto.request.CreateCarBrandRequest;
import com.example.demo.dto.request.CreateCarRequest;
import com.example.demo.dto.request.UpdateCarRequest;
import com.example.demo.dto.response.CarBrandResponse;
import com.example.demo.dto.response.CarResponse;
import com.example.demo.services.ICarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cars")
public class CarController {

    private final ICarService _carService;

    public CarController(ICarService carService){
        _carService = carService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCar(@PathVariable("id") Long id){

        CarResponse carResponse = _carService.getCarById(id);
        if(carResponse != null){
            return new ResponseEntity<>(carResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> updateCar(@PathVariable("id") Long id, @RequestBody UpdateCarRequest request){
        if(_carService.updateCarById(id, request)){
            return new ResponseEntity<>("Updated !", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public CarResponse createCar(@RequestBody CreateCarRequest request){
        return _carService.createCar(request);
    }
}
