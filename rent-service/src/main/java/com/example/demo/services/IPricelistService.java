package com.example.demo.services;

import com.example.demo.dto.request.AddDiscountRequest;
import com.example.demo.dto.request.CreatePricelistRequest;
import com.example.demo.dto.response.PricelistResponse;

public interface IPricelistService {
    PricelistResponse getPricelistById(Long id);

    PricelistResponse createPricelist(CreatePricelistRequest request);

    PricelistResponse addDiscountToPricelist(AddDiscountRequest request);
}
