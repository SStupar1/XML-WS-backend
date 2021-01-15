package com.example.demo.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModel {

    private Long id;
    private String name;
    private CarClass carClass;
    private CarBrand carBrand;
}
