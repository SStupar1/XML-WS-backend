package com.example.demo.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AverageRatingResponse {
    private double averageRating;

    private String carModelName;

    private String agentName;
}
