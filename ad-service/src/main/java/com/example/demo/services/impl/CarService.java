package com.example.demo.services.impl;

import com.example.demo.dto.request.CreateCarRequest;
import com.example.demo.dto.request.UpdateCarRequest;
import com.example.demo.dto.response.CarResponse;
import com.example.demo.entity.Ad;
import com.example.demo.entity.Car;
import com.example.demo.entity.CarModel;
import com.example.demo.repository.ICarModelRepository;
import com.example.demo.repository.ICarRepository;
import com.example.demo.repository.IFuelTypeRepository;
import com.example.demo.repository.IGearshiftTypeRepository;
import com.example.demo.services.ICarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarService implements ICarService {

    private final ICarRepository _carRepository;
    private final ICarModelRepository _carModelRepository;
    private final IFuelTypeRepository _fuelTypeRepository;
    private final IGearshiftTypeRepository _gearshiftTypeRepository;

    public CarService(ICarRepository carRepository, ICarModelRepository carModelRepository, IFuelTypeRepository fuelTypeRepository, IGearshiftTypeRepository gearshiftTypeRepository){
        _carRepository = carRepository;
        _carModelRepository = carModelRepository;
        _fuelTypeRepository = fuelTypeRepository;
        _gearshiftTypeRepository = gearshiftTypeRepository;
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
        Car car = _carRepository.findOneById(id);
        if(request.getKmTraveled() >= car.getKmTraveled()) {
            car.setKmTraveled(request.getKmTraveled());
            _carRepository.save(car);
            return true;
        }
        return false;
    }

    @Override
    public CarResponse createCar(CreateCarRequest request) {
        Car car = new Car();
        car.setKmTraveled(request.getKmTraveled());
        car.setCarModel(_carModelRepository.findOneById(request.getCarModelId()));
        car.setGearshiftType(_gearshiftTypeRepository.findOneById(request.getGearshiftTypeId()));
        car.setFuelType(_fuelTypeRepository.findOneById(request.getFuelTypeId()));
        Car savedCar = _carRepository.save(car);
        return mapCarToResponse(savedCar);
    }

    @Override
    public boolean deleteCarById(Long id) {
        Car car = _carRepository.findOneById(id);
        if(car != null){
            _carRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<CarResponse> getAllCars() {
        List<Car> cars = _carRepository.findAll();

        return  cars.stream()
                .map(car -> mapCarToResponse(car))
                .collect(Collectors.toList());
    }

    private CarResponse mapCarToResponse(Car car) {
        CarResponse carResponse = new CarResponse();
        carResponse.setId(car.getId());
        carResponse.setKmTraveled(car.getKmTraveled());
        carResponse.setCarModelId(car.getCarModel().getId());
        carResponse.setFuelTypeId(car.getFuelType().getId());
        carResponse.setGearshiftTypeId(car.getGearshiftType().getId());
        return carResponse;
    }
}
