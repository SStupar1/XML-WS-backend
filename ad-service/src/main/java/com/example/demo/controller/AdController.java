package com.example.demo.controller;

import com.example.demo.dto.request.*;
import com.example.demo.dto.response.AdResponse;
import com.example.demo.dto.response.PictureResponse;
import com.example.demo.dto.response.TextResponse;
import com.example.demo.services.IAdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("/publisher-ads/{id}/{publisher}")
    public List<AdResponse> getAllPublisherAds(@PathVariable("id") Long id, @PathVariable("publisher") boolean request){
        return _adService.getAllPublisherAds(id,request);
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

    @PostMapping(consumes = { "multipart/form-data" })
    public AdResponse createAd(@RequestPart("imageFile") List<MultipartFile> fileList, @RequestPart("request") CreateAdRequest request) throws Exception{
        System.out.println(fileList);
        System.out.println(request.getSeats());
        return _adService.createAd(fileList, request);
    }

    @GetMapping("/{id}/picture" )
    public ResponseEntity<PictureResponse> getPicture(@PathVariable("id") Long adId) {
        return new ResponseEntity<>(_adService.getPicture(adId), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        _adService.uploadPicture(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public PictureResponse getImage(@PathVariable("id") Long id) throws IOException {
        return _adService.getImage(id);
    }
}
