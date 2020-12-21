package com.example.demo.services.impl;

import com.example.demo.dto.request.CreateFuelTypeRequest;
import com.example.demo.dto.request.UpdateFuelTypeRequest;
import com.example.demo.dto.response.CarModelResponse;
import com.example.demo.dto.response.FuelTypeResponse;
import com.example.demo.entity.CarBrand;
import com.example.demo.entity.CarModel;
import com.example.demo.entity.FuelType;
import com.example.demo.repository.ICarModelRepository;
import com.example.demo.repository.IFuelTypeRepository;
import com.example.demo.services.IFuelTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelTypeService implements IFuelTypeService {

    private final IFuelTypeRepository _fuelTypeRepository;

    public FuelTypeService(IFuelTypeRepository fuelTypeRepository){
        _fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    public List<FuelTypeResponse> getAllFuelTypes() {
        List<FuelType> fuelTypes = _fuelTypeRepository.findAll();
        return  fuelTypes.stream()
                .map(fuelType -> mapFuelTypetoFuelTypeResponse(fuelType))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateCarClassById(Long id, UpdateFuelTypeRequest request) {
        boolean updated = false;
        FuelType fuelType = _fuelTypeRepository.findOneById(id);
        if(fuelType != null) {
            fuelType.setType(request.getType());
            fuelType.setTankCapacity(request.getTankCapacity());
            updated = true;
        }
        _fuelTypeRepository.save(fuelType);

        return updated;
    }

    @Override
    public FuelTypeResponse createFuelType(CreateFuelTypeRequest request) {
        FuelType fuelType = new FuelType();
        fuelType.setType(request.getType());
        fuelType.setTankCapacity(request.getTankCapacity());
        FuelType savedFuelType = _fuelTypeRepository.save(fuelType);
        return mapFuelTypetoFuelTypeResponse(savedFuelType);
    }

    private FuelTypeResponse mapFuelTypetoFuelTypeResponse(FuelType fuelType) {
        FuelTypeResponse response = new FuelTypeResponse();
        response.setId(fuelType.getId());
        response.setType(fuelType.getType());
        response.setTankCapacity(fuelType.getTankCapacity());
        return response;
    }
}
