package com.example.demo.dto.request;

import java.time.LocalDate;

public class CreateAdRequest {
    private Long agentId;

    private Long carId;

    private boolean limitedDistance;

    private int limitedKm;

    private boolean cdw;

    private int seats;

    private LocalDate creationDate;
}
