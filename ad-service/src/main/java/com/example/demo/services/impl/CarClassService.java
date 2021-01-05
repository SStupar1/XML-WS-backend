package com.example.demo.services.impl;

import com.example.demo.dto.request.CreateCarClassRequest;
import com.example.demo.dto.request.UpdateCarClassRequest;
import com.example.demo.dto.response.CarBrandResponse;
import com.example.demo.dto.response.CarClassResponse;
import com.example.demo.entity.CarBrand;
import com.example.demo.entity.CarClass;
import com.example.demo.repository.ICarClassRepository;
import com.example.demo.services.ICarClassService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarClassService implements ICarClassService {

    private final ICarClassRepository _carClassRepository;

    public CarClassService(ICarClassRepository carClassRepository){ _carClassRepository = carClassRepository; }

    @Override
    public List<CarClassResponse> getAllCarClasses() {
        List<CarClass> carClasses = _carClassRepository.findAll();
        return  carClasses.stream()
                .map(carClass -> mapCarClassToCarClassResponse(carClass))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateCarClassById(Long id, UpdateCarClassRequest request) {
        CarClass carClass = _carClassRepository.findOneById(id);
        if(carClass != null) {
            carClass.setName(request.getName());
            _carClassRepository.save(carClass);
            return true;
        }
        return false;
    }

    @Override
    public CarClassResponse createCarClass(CreateCarClassRequest request) {
        CarClass carClass = new CarClass();
        carClass.setName(request.getName());
        CarClass savedCarClass = _carClassRepository.save(carClass);
        return mapCarClassToCarClassResponse(savedCarClass);
    }

    @Override
    public boolean deleteCarClassById(Long id) {
        CarClass carClass = _carClassRepository.findOneById(id);
        if(carClass != null){
            _carClassRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CarClassResponse getCarClassById(Long id) {
        CarClass carClass = _carClassRepository.findOneById(id);
        if(carClass != null) {
            return mapCarClassToCarClassResponse(carClass);
        }
        return null;
    }

    public CarClassResponse mapCarClassToCarClassResponse(CarClass carClass) {
        CarClassResponse response = new CarClassResponse();
        response.setId(carClass.getId());
        response.setName(carClass.getName());
        return response;
    }
}
