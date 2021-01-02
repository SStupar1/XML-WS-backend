package com.example.demo.services;

import com.example.demo.dto.request.CreateCarRequest;
import com.example.demo.dto.request.UpdateCarRequest;
import com.example.demo.dto.response.CarResponse;

import java.util.List;

public interface ICarService {
    CarResponse getCarById(Long id);

    boolean updateCarById(Long id, UpdateCarRequest request);

    CarResponse createCar(CreateCarRequest request);

    boolean deleteCarById(Long id);

    List<CarResponse> getAllCars();
}
