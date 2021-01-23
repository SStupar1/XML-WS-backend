package com.example.demo.services;

import com.example.demo.dto.response.PriceResponse;

public interface IPriceService {
    PriceResponse calculateStartingPricePerAd(double priceCDW,boolean availableCDW, double pricePerDay, double discount, String fromDateString, String toDateString);
}
