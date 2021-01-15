package com.example.demo.dto.request;

import com.example.demo.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationListRequest {
    List<ReservationRequest> reservationRequestList;
}
