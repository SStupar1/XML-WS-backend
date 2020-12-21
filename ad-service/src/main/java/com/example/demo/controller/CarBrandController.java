package com.example.demo.controller;


import com.example.demo.dto.request.CreateCarBrandRequest;
import com.example.demo.dto.request.UpdateCarBrandRequest;
import com.example.demo.dto.response.CarBrandResponse;
import com.example.demo.services.ICarBrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car-brands")
public class CarBrandController {

    private final ICarBrandService _carBrandService;

    public CarBrandController(ICarBrandService carBrandService){
        _carBrandService = carBrandService;
    }

    @GetMapping()
    public List<CarBrandResponse> getAllCarBrands(){
        return _carBrandService.getAllCarBrands();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarBrand(@PathVariable("id") Long id, @RequestBody UpdateCarBrandRequest request){
        if(_carBrandService.updateCarBrandById(id, request)){
            return new ResponseEntity<>("Updated !", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public CarBrandResponse createCarBrand(@RequestBody CreateCarBrandRequest request){
        return _carBrandService.createCarBrand(request);
    }
}
