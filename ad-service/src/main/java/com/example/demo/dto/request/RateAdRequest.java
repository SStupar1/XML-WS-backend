package com.example.demo.dto.request;

import lombok.Data;



@Data
public class RateAdRequest {

    private Long simpleUserId;

    private double grade;

    private Long adId;

}
