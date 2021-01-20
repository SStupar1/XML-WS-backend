package com.example.demo.services;

import com.example.demo.dto.request.CreatePricelistRequest;
import com.example.demo.dto.response.PricelistResponse;

import java.util.List;

public interface IPricelistService {
    PricelistResponse getPricelistById(Long id);

    PricelistResponse createPricelist(CreatePricelistRequest request);

    List<PricelistResponse> getAllPricelists();
}
