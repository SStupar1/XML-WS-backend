package com.example.demo.dto.response;

import com.example.demo.dto.client.Agent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdResponse {

    private Long id;

    private String name;

    private boolean limitedDistance;

    private int limitedKm;

    private boolean cdw;

    private int seats;

    private LocalDate creationDate;

    private CarResponse car;

    private boolean simpleUser;

    private PublisherResponse publisher;

}
