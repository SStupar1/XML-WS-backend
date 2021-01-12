package com.example.demo.services;

import com.example.demo.dto.request.RateAdRequest;
import com.example.demo.dto.response.AverageRatingResponse;
import com.example.demo.dto.response.RatingResponse;

import java.util.List;
import java.util.UUID;

public interface IRatingService {
    List<RatingResponse> getAllRatingsByAd(Long id);

    RatingResponse rateAd(RateAdRequest request);

    AverageRatingResponse getAverageRatingByAd(Long id);
}
