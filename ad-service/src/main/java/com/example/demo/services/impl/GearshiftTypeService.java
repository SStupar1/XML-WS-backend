package com.example.demo.services.impl;

import com.example.demo.dto.request.CreateGearshiftTypeRequest;
import com.example.demo.dto.request.UpdateGearshiftRequest;
import com.example.demo.dto.response.GearShiftTypeResponse;
import com.example.demo.entity.CarBrand;
import com.example.demo.entity.FuelType;
import com.example.demo.entity.GearshiftType;
import com.example.demo.repository.IGearshiftTypeRepository;
import com.example.demo.services.IGearshiftTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GearshiftTypeService implements IGearshiftTypeService {

    private final IGearshiftTypeRepository _gearshiftTypeRepository;

    public GearshiftTypeService(IGearshiftTypeRepository gearshiftTypeRepository){
        _gearshiftTypeRepository = gearshiftTypeRepository;
    }

    @Override
    public List<GearShiftTypeResponse> getAllGearshiftTypes() {
        List<GearshiftType> gearshiftTypes = _gearshiftTypeRepository.findAll();
        return  gearshiftTypes.stream()
                .map(gearshiftType -> mapGearshiftTypeToGearshiftTypeRepository(gearshiftType))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateCarClassById(Long id, UpdateGearshiftRequest request) {
        boolean updated = false;
        GearshiftType gearshiftType = _gearshiftTypeRepository.findOneById(id);
        if(gearshiftType != null) {
            gearshiftType.setType(request.getType());
            gearshiftType.setNumberOfGears(request.getNumberOfGears());
            updated = true;
        }
        _gearshiftTypeRepository.save(gearshiftType);

        return updated;
    }

    @Override
    public GearShiftTypeResponse createGearshiftType(CreateGearshiftTypeRequest request) {
        GearshiftType gearshiftType = new GearshiftType();
        gearshiftType.setType(request.getType());
        gearshiftType.setNumberOfGears(request.getNumberOfGears());
        GearshiftType savedGearshiftType = _gearshiftTypeRepository.save(gearshiftType);
        return mapGearshiftTypeToGearshiftTypeRepository(savedGearshiftType);
    }

    private GearShiftTypeResponse mapGearshiftTypeToGearshiftTypeRepository(GearshiftType gearshiftType) {
        GearShiftTypeResponse response = new GearShiftTypeResponse();
        response.setId(gearshiftType.getId());
        response.setType(gearshiftType.getType());
        response.setNumberOfGears(gearshiftType.getNumberOfGears());
        return response;
    }
}
