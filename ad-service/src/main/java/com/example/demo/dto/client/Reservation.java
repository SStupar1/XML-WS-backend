package com.example.demo.dto.client;

import com.example.demo.dto.response.AdResponse;
import com.example.demo.entity.Ad;
import com.example.demo.util.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Long id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;
    private Long customerId;
    private AdResponse ad;
    private ReservationStatus status;
    private boolean simpleUser;

}


