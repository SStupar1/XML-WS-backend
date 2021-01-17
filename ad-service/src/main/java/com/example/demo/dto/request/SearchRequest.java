package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    private String address;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;
    private Long carBrandId;
    private Long carClassId;
    private Long carModelId;
    private Long fuelTypeId;
    private Long gearshiftTypeId;
    private int minPrice;
    private int maxPrice;
    private int kmTraveled;
    private int limitedKm;
    private boolean availableCDW;
    private int seats;
}
