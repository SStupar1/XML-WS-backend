package com.example.demo.dto.response;

import com.example.demo.dto.client.Ad;
import com.example.demo.util.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class ReservationResponse {

    private Long id;

    private Ad ad;

    private LocalDate fromDate;

    private LocalDate toDate;

    private LocalTime fromTime;

    private LocalTime toTime;

    private boolean simpleUser;

    private ReservationStatus status;

    private Long customerId;
}
