package com.example.demo.dto.request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDiscountRequest {
    private Long pricelistId;
    private Long discountId;
}
