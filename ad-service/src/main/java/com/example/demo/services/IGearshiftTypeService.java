package com.example.demo.services;

import com.example.demo.dto.request.UpdateGearshiftRequest;
import com.example.demo.dto.response.FuelTypeResponse;
import com.example.demo.dto.response.GearShiftTypeResponse;

import java.util.List;

public interface IGearshiftTypeService {
    List<GearShiftTypeResponse> getAllGearshiftTypes();

    boolean updateCarClassById(Long id, UpdateGearshiftRequest request);
}
