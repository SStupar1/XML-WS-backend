package com.example.demo.services;

import com.example.demo.dto.request.CreateDiscountRequest;
import com.example.demo.dto.response.DiscountResponse;

import java.util.List;

public interface IDiscountService {

    DiscountResponse getDiscountById(Long id);

    List<DiscountResponse> getAllDiscounts();
}
