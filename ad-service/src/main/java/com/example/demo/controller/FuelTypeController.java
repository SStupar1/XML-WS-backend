package com.example.demo.controller;

import com.example.demo.dto.request.UpdateCarClassRequest;
import com.example.demo.dto.request.UpdateFuelTypeRequest;
import com.example.demo.dto.response.CarModelResponse;
import com.example.demo.dto.response.FuelTypeResponse;
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
        if(_fuelTypeService.updateCarClassById(id, request)){
            return new ResponseEntity<>("Updated !", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

}
