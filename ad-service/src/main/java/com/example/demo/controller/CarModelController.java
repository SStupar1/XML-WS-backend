package com.example.demo.controller;

import com.example.demo.dto.request.CreateCarBrandRequest;
import com.example.demo.dto.request.CreateCarModelRequest;
import com.example.demo.dto.request.UpdateCarBrandRequest;
import com.example.demo.dto.request.UpdateCarModelRequest;
import com.example.demo.dto.response.CarBrandResponse;
import com.example.demo.dto.response.CarModelResponse;
import com.example.demo.services.ICarBrandService;
import com.example.demo.services.ICarModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car-models")
public class CarModelController {

    private final ICarModelService _carModelService;

    public CarModelController(ICarModelService carModelService){
        _carModelService = carModelService;
    }

    @GetMapping()
    public List<CarModelResponse> getAllCarModels(){
        return _carModelService.getAllCarModels();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarModel(@PathVariable("id") Long id, @RequestBody UpdateCarModelRequest request){
        if(_carModelService.updateCarBrandById(id, request)){
            return new ResponseEntity<>("Updated !", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping()
    public CarModelResponse createCarModel(@RequestBody CreateCarModelRequest request){
        return _carModelService.createCarModel(request);
    }
}
