package com.example.demo.controller;


import com.example.demo.dto.request.CreateGearshiftTypeRequest;
import com.example.demo.dto.request.UpdateGearshiftRequest;
import com.example.demo.dto.response.GearshiftTypeResponse;
import com.example.demo.dto.response.TextResponse;
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
    public List<GearshiftTypeResponse> getAllGearshiftTypes(){
        return _gearshiftTypeService.getAllGearshiftTypes();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGearshiftType(@PathVariable("id") Long id, @RequestBody UpdateGearshiftRequest request){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Updated !");
        if(_gearshiftTypeService.updateCarClassById(id, request)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Gearshift type doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGearshiftType(@PathVariable("id") Long id){
        GearshiftTypeResponse gearshiftTypeResponse = _gearshiftTypeService.getGearshiftTypeById(id);
        if(gearshiftTypeResponse != null){
            return new ResponseEntity<>(gearshiftTypeResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car brand doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
        public GearshiftTypeResponse createGearshiftType(@RequestBody CreateGearshiftTypeRequest request){
        return _gearshiftTypeService.createGearshiftType(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGearshiftType(@PathVariable("id") Long id){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Deleted !");
        if(_gearshiftTypeService.deleteGearshiftTypeById(id)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Gearshift type doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}
