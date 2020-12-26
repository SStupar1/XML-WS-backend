package com.example.demo.services;

import com.example.demo.dto.request.CreateAdRequest;
import com.example.demo.dto.request.UpdateAdRequest;
import com.example.demo.dto.response.AdResponse;

import java.util.List;

public interface IAdService {
    AdResponse getAdById(Long id);

    boolean deleteAdById(Long id);

    AdResponse createAd(CreateAdRequest request);

    List<AdResponse> getAllAds();

    boolean updateAdById(Long id, UpdateAdRequest request);
}
