package com.example.demo.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pricelist {
    private Long id;
    private double pricePerDay;
    private double pricePerKilometer;
    private double priceCDW;
    private int discountId;
}
