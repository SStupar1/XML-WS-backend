package com.example.demo.services;

import com.example.demo.dto.request.CreateCarModelRequest;
import com.example.demo.dto.request.UpdateCarModelRequest;
import com.example.demo.dto.response.CarModelResponse;

import java.util.List;

public interface ICarModelService {
    List<CarModelResponse> getAllCarModels();

    boolean updateCarModelById(Long id, UpdateCarModelRequest request);

    CarModelResponse createCarModel(CreateCarModelRequest request);

    boolean deleteCarModelById(Long id);

    CarModelResponse getCarModelById(Long id);
}
