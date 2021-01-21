package com.example.demo.dto.soap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFuelTypeRequestSOAP {
    private Long id;
    private String type;
    private String tankCapacity;
}
