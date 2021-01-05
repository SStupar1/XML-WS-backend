package com.example.demo.dto.response;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {

    private Long id;

    private int kmTraveled;

    private FuelTypeResponse fuelType;

    private GearshiftTypeResponse gearshiftType;

    private CarModelResponse carModel;

}
