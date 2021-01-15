package com.example.demo.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePricelistRequest {

    private double pricePerDay;

    private double pricePerKilometer;

    private double priceCDW;


}
