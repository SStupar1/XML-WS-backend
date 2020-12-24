package com.example.demo.services;

import com.example.demo.dto.request.CreateGearshiftTypeRequest;
import com.example.demo.dto.request.UpdateGearshiftRequest;
import com.example.demo.dto.response.GearshiftTypeResponse;

import java.util.List;

public interface IGearshiftTypeService {
    List<GearshiftTypeResponse> getAllGearshiftTypes();

    boolean updateCarClassById(Long id, UpdateGearshiftRequest request);

    GearshiftTypeResponse createGearshiftType(CreateGearshiftTypeRequest request);

    boolean deleteGearshiftTypeById(Long id);

    GearshiftTypeResponse getGearshiftTypeById(Long id);
}
