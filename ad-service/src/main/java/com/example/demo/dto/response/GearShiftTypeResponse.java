package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GearShiftTypeResponse {

    private Long id;

    private String type;

    private int numberOfGears;

}
