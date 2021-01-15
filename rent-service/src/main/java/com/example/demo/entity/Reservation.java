package com.example.demo.entity;

import com.example.demo.util.enums.ReservationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fromDate;

    private LocalDate toDate;

    private LocalTime fromTime;

    private LocalTime toTime;

    @Column(name = "customer_id")
    private Long customerID;

    @Column(name = "ad_id")
    private Long adID;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;


}
