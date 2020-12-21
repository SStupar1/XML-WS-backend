package com.example.demo.controller;

import com.example.demo.dto.request.CreateCarBrandRequest;
import com.example.demo.dto.request.CreateGearshiftTypeRequest;
import com.example.demo.dto.request.UpdateFuelTypeRequest;
import com.example.demo.dto.request.UpdateGearshiftRequest;
import com.example.demo.dto.response.CarBrandResponse;
import com.example.demo.dto.response.GearShiftTypeResponse;
import com.example.demo.services.IGearshiftTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gearshift-types")
public class GearshiftTypeController {
    private final IGearshiftTypeService _gearshiftTypeService;

    public GearshiftTypeController(IGearshiftTypeService gearshiftTypeService){
        _gearshiftTypeService = gearshiftTypeService;
    }

    @GetMapping()
    public List<GearShiftTypeResponse> getAllGearshiftTypes(){
        return _gearshiftTypeService.getAllGearshiftTypes();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGearshiftType(@PathVariable("id") Long id, @RequestBody UpdateGearshiftRequest request){
        if(_gearshiftTypeService.updateCarClassById(id, request)){
            return new ResponseEntity<>("Updated !", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping()
        public GearShiftTypeResponse createGearshiftType(@RequestBody CreateGearshiftTypeRequest request){
        return _gearshiftTypeService.createGearshiftType(request);
    }
}
