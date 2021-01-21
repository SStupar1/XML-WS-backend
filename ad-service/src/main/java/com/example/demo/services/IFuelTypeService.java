package com.example.demo.services;

import com.example.demo.dto.request.CreateFuelTypeRequest;
import com.example.demo.dto.request.UpdateFuelTypeRequest;
import com.example.demo.dto.response.CarModelResponse;
import com.example.demo.dto.response.FuelTypeResponse;
import com.example.demo.dto.soap.CreateFuelTypeRequestSOAP;

import java.util.List;

public interface IFuelTypeService {
    List<FuelTypeResponse> getAllFuelTypes();

    boolean updateFuelTypeById(Long id, UpdateFuelTypeRequest request);

    FuelTypeResponse createFuelType(CreateFuelTypeRequest request);

    boolean deleteFuelTypeById(Long id);

    FuelTypeResponse getFuelTypeById(Long id);

    void createFuelTypeViaSOAP(CreateFuelTypeRequestSOAP request);
}
