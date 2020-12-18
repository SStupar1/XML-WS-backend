package com.example.demo.services.impl;

import com.example.demo.dto.request.UpdateCarRequest;
import com.example.demo.dto.response.CarResponse;
import com.example.demo.entity.Car;
import com.example.demo.repository.ICarRepository;
import com.example.demo.services.ICarService;
import org.springframework.stereotype.Service;

@Service
public class CarService implements ICarService {

    private final ICarRepository _carRepository;

    public CarService(ICarRepository carRepository){
        _carRepository = carRepository;
    }

    @Override
    public CarResponse getCarById(Long id) {
        Car car = _carRepository.findOneById(id);
        if(car != null){
            return mapCarToResponse(car);
        }
        else{
            return null;
        }
    }

    @Override
    public boolean updateCarById(Long id, UpdateCarRequest request) {
        boolean updated = false;
        Car car = _carRepository.findOneById(id);
        if(request.getKmTraveled() >= car.getKmTraveled()) {
            car.setKmTraveled(request.getKmTraveled());
            updated = true;
            _carRepository.save(car);
        }

        return updated;
    }

    private CarResponse mapCarToResponse(Car car) {
        CarResponse carResponse = new CarResponse();
        carResponse.setId(car.getId());
        carResponse.setKmTraveled(car.getKmTraveled());

        return carResponse;
    }
}
