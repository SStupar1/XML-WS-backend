package com.example.demo.services.impl;

import com.example.demo.dto.request.UpdateCarModelRequest;
import com.example.demo.dto.response.CarBrandResponse;
import com.example.demo.dto.response.CarModelResponse;
import com.example.demo.entity.CarBrand;
import com.example.demo.entity.CarClass;
import com.example.demo.entity.CarModel;
import com.example.demo.repository.ICarBrandRepository;
import com.example.demo.repository.ICarModelRepository;
import com.example.demo.services.ICarModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarModelService implements ICarModelService {

    private final ICarModelRepository  _carModelRepository;

    public CarModelService(ICarModelRepository carModelRepository){
        _carModelRepository = carModelRepository;
    }

    @Override
    public List<CarModelResponse> getAllCarModels() {
        List<CarModel> carModels = _carModelRepository.findAll();
        return  carModels.stream()
                .map(carBrand -> mapCarModelToCarModelResponse(carBrand))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateCarBrandById(Long id, UpdateCarModelRequest request) {
        boolean updated = false;
        CarModel carModel = _carModelRepository.findOneById(id);
        carModel.setName(request.getName());
        updated = true;
        _carModelRepository.save(carModel);

        return updated;
    }

    private CarModelResponse mapCarModelToCarModelResponse(CarModel carModel) {
        CarModelResponse response = new CarModelResponse();
        response.setId(carModel.getId());
        response.setName(carModel.getName());
        return response;
    }

}
