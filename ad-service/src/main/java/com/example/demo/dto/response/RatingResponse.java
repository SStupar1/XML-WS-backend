package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {

    private Long ratingId;

    private double grade; //rating

    private String customerFirstName;

    private String customerLastName;

    private String customerEmail;

    private String agentName;

    private String agentEmail;

    private String carBrandName;

    private String carModelName;


}
