package com.example.demo.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private Long id;

    private int kmTraveled;

    private FuelType fuelType;

    private GearshiftType gearshiftType;

    private CarModel carModel;
}
