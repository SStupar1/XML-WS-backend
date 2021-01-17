package com.example.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;


@Getter
@Setter
public class PricelistResponse {

    private Long id;

    private double pricePerDay;

    private double pricePerKilometer;

    private double priceCdw;

    private int discountId;
}
