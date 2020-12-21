package com.example.demo.services;

import com.example.demo.dto.request.UpdateCarClassRequest;
import com.example.demo.dto.response.CarBrandResponse;
import com.example.demo.dto.response.CarClassResponse;

import java.util.List;

public interface ICarClassService {
    List<CarClassResponse> getAllCarClasses();

    boolean updateCarClassById(Long id, UpdateCarClassRequest request);
}
