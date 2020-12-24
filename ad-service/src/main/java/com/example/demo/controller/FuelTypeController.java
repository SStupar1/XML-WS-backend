package com.example.demo.controller;

import com.example.demo.dto.request.CreateCarBrandRequest;
import com.example.demo.dto.request.CreateFuelTypeRequest;
import com.example.demo.dto.request.UpdateCarClassRequest;
import com.example.demo.dto.request.UpdateFuelTypeRequest;
import com.example.demo.dto.response.CarBrandResponse;
import com.example.demo.dto.response.CarModelResponse;
import com.example.demo.dto.response.FuelTypeResponse;
import com.example.demo.dto.response.TextResponse;
import com.example.demo.services.ICarModelService;
import com.example.demo.services.IFuelTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fuel-types")
public class FuelTypeController {
    private final IFuelTypeService _fuelTypeService;

    public FuelTypeController(IFuelTypeService fuelTypeService){
        _fuelTypeService = fuelTypeService;
    }

    @GetMapping()
    public List<FuelTypeResponse> getAllFuelTypes(){
        return _fuelTypeService.getAllFuelTypes();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFuelType(@PathVariable("id") Long id, @RequestBody UpdateFuelTypeRequest request){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Updated !");
        if(_fuelTypeService.updateFuelTypeById(id, request)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Fuel type doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFuelType(@PathVariable("id") Long id){
        FuelTypeResponse fuelTypeResponse = _fuelTypeService.getFuelTypeById(id);
        if(fuelTypeResponse != null){
            return new ResponseEntity<>(fuelTypeResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Fuel type doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public FuelTypeResponse createFuelType(@RequestBody CreateFuelTypeRequest request){
        return _fuelTypeService.createFuelType(request);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFuelType(@PathVariable("id") Long id){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Deleted !");
        if(_fuelTypeService.deleteFuelTypeById(id)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Fuel type doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}
