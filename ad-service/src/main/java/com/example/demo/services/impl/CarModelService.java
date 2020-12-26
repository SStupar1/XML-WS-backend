package com.example.demo.services.impl;

import com.example.demo.dto.request.CreateCarModelRequest;
import com.example.demo.dto.request.UpdateCarModelRequest;
import com.example.demo.dto.response.CarBrandResponse;
import com.example.demo.dto.response.CarModelResponse;
import com.example.demo.entity.CarBrand;
import com.example.demo.entity.CarClass;
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
                .map(carModel -> mapCarModelToCarModelResponse(carModel))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateCarModelById(Long id, UpdateCarModelRequest request) {
        CarModel carModel = _carModelRepository.findOneById(id);
        if(carModel != null) {
            carModel.setName(request.getName());
            CarBrand carBrand = _carBrandRepository.findOneById(request.getCarBrandId());
            CarClass carClass = _carClassRepository.findOneById(request.getCarClassId());
            carModel.setCarBrand(carBrand);
            carModel.setCarClass(carClass);
            _carModelRepository.save(carModel);
            return true;
        }
        return false;
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

    @Override
    public boolean deleteCarModelById(Long id) {
        CarModel carModel = _carModelRepository.findOneById(id);
        if(carModel != null){
            _carModelRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CarModelResponse getCarModelById(Long id) {
        CarModel carModel = _carModelRepository.findOneById(id);
        if(carModel != null) {
            return mapCarModelToCarModelResponse(carModel);
        }
        return null;
    }


    private CarModelResponse mapCarModelToCarModelResponse(CarModel carModel) {
        CarModelResponse response = new CarModelResponse();
        response.setId(carModel.getId());
        response.setName(carModel.getName());
        response.setCarBrandId(carModel.getCarBrand().getId());
        response.setCarClassId(carModel.getCarClass().getId());
        return response;
    }

}
