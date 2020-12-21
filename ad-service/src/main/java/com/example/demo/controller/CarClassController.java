package com.example.demo.controller;

import com.example.demo.dto.request.UpdateCarBrandRequest;
import com.example.demo.dto.request.UpdateCarClassRequest;
import com.example.demo.dto.response.CarBrandResponse;
import com.example.demo.dto.response.CarClassResponse;
import com.example.demo.services.ICarClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car-classes")
public class CarClassController {

    private final ICarClassService _carClassService;

    public CarClassController(ICarClassService carClassService){ _carClassService = carClassService; }

    @GetMapping()
    public List<CarClassResponse> getAllCarClasses(){
        return _carClassService.getAllCarClasses();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarClass(@PathVariable("id") Long id, @RequestBody UpdateCarClassRequest request){
        if(_carClassService.updateCarClassById(id, request)){
            return new ResponseEntity<>("Updated !", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}
