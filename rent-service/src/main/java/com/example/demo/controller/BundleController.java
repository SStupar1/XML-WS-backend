package com.example.demo.controller;

import com.example.demo.dto.request.RequestId;
import com.example.demo.dto.request.ReservationListRequest;
import com.example.demo.dto.request.ReservationRequest;
import com.example.demo.dto.response.BundleResponse;
import com.example.demo.dto.response.StringResponse;
import com.example.demo.services.IBundleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bundles")
public class BundleController {

    private final IBundleService _bundleService;

    public BundleController(IBundleService bundleService) {
        _bundleService = bundleService;
    }

    @GetMapping()
    public List<BundleResponse> getPublisherBundles(@RequestBody RequestId request){
        return _bundleService.getPublisherBundles(request);
    }

    @PostMapping()
    public ResponseEntity<?> createBundle(@RequestBody ReservationListRequest request){
        StringResponse response = new StringResponse();
        if(_bundleService.createBundle(request.getReservationRequestList())){
            response.setText("created bundle");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.setText("error creating bundle");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
