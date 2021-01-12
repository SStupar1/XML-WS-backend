package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePricelistRequest {

    private double pricePerDay;

    private double pricePerKilometer;

    private double priceCDW;


}
