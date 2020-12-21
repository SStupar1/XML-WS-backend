package com.example.demo.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {

    private Long id;

    private int kmTraveled;

    private Long fuelTypeId;

    private Long gearshiftTypeId;

    private Long carModelId;

}
