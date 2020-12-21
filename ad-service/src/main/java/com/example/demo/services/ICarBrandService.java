package com.example.demo.services;

import com.example.demo.dto.request.CreateCarBrandRequest;
import com.example.demo.dto.request.UpdateCarBrandRequest;
import com.example.demo.dto.response.CarBrandResponse;
import java.util.List;

public interface ICarBrandService {
    List<CarBrandResponse> getAllCarBrands();

    boolean updateCarBrandById(Long id, UpdateCarBrandRequest request);

    CarBrandResponse createCarBrand(CreateCarBrandRequest request);
}
