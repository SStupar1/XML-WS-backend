package com.example.demo.dto.request;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDiscountRequest {
    private Long pricelistId;
    private Long discountId;
}
