package com.example.demo.services;

import com.example.demo.dto.request.CreateFuelTypeRequest;
import com.example.demo.dto.request.UpdateFuelTypeRequest;
import com.example.demo.dto.response.CarModelResponse;
import com.example.demo.dto.response.FuelTypeResponse;

import java.util.List;

public interface IFuelTypeService {
    List<FuelTypeResponse> getAllFuelTypes();

    boolean updateCarClassById(Long id, UpdateFuelTypeRequest request);

    FuelTypeResponse createFuelType(CreateFuelTypeRequest request);
}