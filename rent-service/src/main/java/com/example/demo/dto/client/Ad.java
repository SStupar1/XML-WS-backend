package com.example.demo.dto.client;

//import jdk.jfr.SettingDefinition;
import com.example.demo.dto.response.PricelistResponse;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ad {

    private Long id;
    private String name;
    private boolean limitedDistance;
    private int limitedKm;
    private boolean cdw;
    private int seats;
    private LocalDate creationDate;
    private Car car;
    private boolean simpleUser;
    private Publisher publisher;
    private PricelistResponse pricelist;
}
