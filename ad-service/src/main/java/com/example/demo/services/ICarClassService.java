package com.example.demo.services;

import com.example.demo.dto.request.CreateCarClassRequest;
import com.example.demo.dto.request.UpdateCarClassRequest;
import com.example.demo.dto.response.CarClassResponse;

import java.util.List;

public interface ICarClassService {
    List<CarClassResponse> getAllCarClasses();

    boolean updateCarClassById(Long id, UpdateCarClassRequest request);

    CarClassResponse createCarClass(CreateCarClassRequest request);
}
