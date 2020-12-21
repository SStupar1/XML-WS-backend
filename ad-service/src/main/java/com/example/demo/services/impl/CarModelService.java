package com.example.demo.services.impl;

import com.example.demo.dto.request.CreateCarModelRequest;
import com.example.demo.dto.request.UpdateCarModelRequest;
import com.example.demo.dto.response.CarModelResponse;
import com.example.demo.entity.CarModel;
import com.example.demo.repository.ICarBrandRepository;
import com.example.demo.repository.ICarClassRepository;
import com.example.demo.repository.ICarModelRepository;
import com.example.demo.services.ICarModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarModelService implements ICarModelService {

    private final ICarModelRepository  _carModelRepository;
    private final ICarBrandRepository _carBrandRepository;
    private final ICarClassRepository _carClassRepository;

    public CarModelService(ICarModelRepository carModelRepository, ICarBrandRepository carBrandRepository, ICarClassRepository carClassRepository){
        _carModelRepository = carModelRepository;
        _carBrandRepository = carBrandRepository;
        _carClassRepository = carClassRepository;
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
        if(carModel != null) {
            carModel.setName(request.getName());
            updated = true;
        }
        _carModelRepository.save(carModel);

        return updated;
    }

    @Override
    public CarModelResponse createCarModel(CreateCarModelRequest request) {
        CarModel carModel = new CarModel();
        carModel.setName(request.getName());
        carModel.setCarBrand(_carBrandRepository.findOneById(request.getCarBrandId()));
        carModel.setCarClass(_carClassRepository.findOneById(request.getCarClassId()));
        CarModel savedCarModel = _carModelRepository.save(carModel);
        return mapCarModelToCarModelResponse(savedCarModel);
    }

    private CarModelResponse mapCarModelToCarModelResponse(CarModel carModel) {
        CarModelResponse response = new CarModelResponse();
        response.setId(carModel.getId());
        response.setName(carModel.getName());
        return response;
    }

}
