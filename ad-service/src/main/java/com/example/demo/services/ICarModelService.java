package com.example.demo.services;

import com.example.demo.dto.request.UpdateCarModelRequest;
import com.example.demo.dto.response.CarModelResponse;

import java.util.List;

public interface ICarModelService {
    List<CarModelResponse> getAllCarModels();

    boolean updateCarBrandById(Long id, UpdateCarModelRequest request);
}
