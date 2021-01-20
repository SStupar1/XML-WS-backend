package com.example.demo.dto.response;

import com.example.demo.util.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BundleResponse {
    private Long id;

    private List<ReservationResponse> reservations;

    private Long publisherId;

    private ReservationStatus status;
}
