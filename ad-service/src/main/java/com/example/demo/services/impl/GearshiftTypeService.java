package com.example.demo.services.impl;

import com.example.demo.dto.request.CreateGearshiftTypeRequest;
import com.example.demo.dto.request.UpdateGearshiftRequest;
import com.example.demo.dto.response.GearshiftTypeResponse;
import com.example.demo.entity.CarBrand;
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
    public List<GearshiftTypeResponse> getAllGearshiftTypes() {
        List<GearshiftType> gearshiftTypes = _gearshiftTypeRepository.findAll();
        return  gearshiftTypes.stream()
                .map(gearshiftType -> mapGearshiftTypeToGearshiftTypeRepository(gearshiftType))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateCarClassById(Long id, UpdateGearshiftRequest request) {
        GearshiftType gearshiftType = _gearshiftTypeRepository.findOneById(id);
        if(gearshiftType != null) {
            gearshiftType.setType(request.getType());
            gearshiftType.setNumberOfGears(request.getNumberOfGears());
            _gearshiftTypeRepository.save(gearshiftType);
            return true;
        }
        return false;
    }

    @Override
    public GearshiftTypeResponse createGearshiftType(CreateGearshiftTypeRequest request) {
        GearshiftType gearshiftType = new GearshiftType();
        gearshiftType.setType(request.getType());
        gearshiftType.setNumberOfGears(request.getNumberOfGears());
        GearshiftType savedGearshiftType = _gearshiftTypeRepository.save(gearshiftType);
        return mapGearshiftTypeToGearshiftTypeRepository(savedGearshiftType);
    }

    @Override
    public boolean deleteGearshiftTypeById(Long id) {
        GearshiftType gearshiftType = _gearshiftTypeRepository.findOneById(id);
        if(gearshiftType != null){
            _gearshiftTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public GearshiftTypeResponse getGearshiftTypeById(Long id) {
        GearshiftType gearshiftType = _gearshiftTypeRepository.findOneById(id);
        if(gearshiftType != null) {
            return mapGearshiftTypeToGearshiftTypeRepository(gearshiftType);
        }
        return null;
    }

    private GearshiftTypeResponse mapGearshiftTypeToGearshiftTypeRepository(GearshiftType gearshiftType) {
        GearshiftTypeResponse response = new GearshiftTypeResponse();
        response.setId(gearshiftType.getId());
        response.setType(gearshiftType.getType());
        response.setNumberOfGears(gearshiftType.getNumberOfGears());
        return response;
    }
}
