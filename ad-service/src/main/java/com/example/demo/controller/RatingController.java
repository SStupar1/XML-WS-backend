package com.example.demo.controller;

import com.example.demo.dto.request.RateAdRequest;
import com.example.demo.dto.response.AverageRatingResponse;
import com.example.demo.dto.response.RatingResponse;
import com.example.demo.services.IRatingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final IRatingService _ratingService;

    public RatingController(IRatingService ratingService) {
        _ratingService = ratingService;
    }

    @GetMapping("/{id}/ad")
    List<RatingResponse> getAllRatingsByAd(@PathVariable Long id) throws Exception{
        return _ratingService.getAllRatingsByAd(id);
    }

    @PostMapping()
    RatingResponse rateAd(@RequestBody RateAdRequest request) throws Exception{
        return _ratingService.rateAd(request);
    }

    @GetMapping("/average/{id}/ad")
    AverageRatingResponse getAverageRatingByAd(@PathVariable Long id) throws Exception{
        return _ratingService.getAverageRatingByAd(id);
    }
}
