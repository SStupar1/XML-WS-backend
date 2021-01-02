package com.example.demo.services.impl;

import com.example.demo.client.AuthClient;
import com.example.demo.dto.client.SimpleUser;
import com.example.demo.dto.request.CreateAdRequest;
import com.example.demo.dto.request.PublisherAdsRequest;
import com.example.demo.dto.request.UpdateAdRequest;
import com.example.demo.dto.response.AdResponse;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.services.IAdService;
import com.example.demo.util.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdService implements IAdService {

    private final IAdRepository _adRepository;
    private final ICarModelRepository _carModelRepository;
    private final IFuelTypeRepository _fuelTypeRepository;
    private final IGearshiftTypeRepository _gearshiftTypeRepository;
    private final ICarRepository _carRepository;
    private final AuthClient _authClient;

    public AdService(IAdRepository adRepository, ICarModelRepository carModelRepository, IFuelTypeRepository fuelTypeRepository, IGearshiftTypeRepository gearshiTypeRepository, ICarRepository carRepository, AuthClient authClient){
        _adRepository = adRepository;
        _carModelRepository = carModelRepository;
        _fuelTypeRepository = fuelTypeRepository;
        _gearshiftTypeRepository = gearshiTypeRepository;
        _carRepository = carRepository;
        _authClient = authClient;
    }

    @Override
    public boolean deleteAdById(Long id) {
        Ad ad = _adRepository.findOneById(id);
        if(ad != null){
            Car car = _carRepository.findOneById(ad.getCar().getId());
            _carRepository.deleteById(car.getId());
            return true;
        }
        return false;
    }

    @Override
    public AdResponse createAd(CreateAdRequest request) {

        if(request.isSimpleUser()) {
            SimpleUser simpleUser = _authClient.getSimpleUser(request.getPublisherId());
            if (simpleUser.getNumOfAds() >= 3) {
                throw new GeneralException("You can not create more than 3 advertisements.", HttpStatus.BAD_REQUEST);
            }
            _authClient.increaseNumOfAds(simpleUser.getId());
        }

        Car car = new Car();
        CarModel carModel = _carModelRepository.findOneById(request.getCarModelId());
        car.setCarModel(carModel);
        car.setKmTraveled(request.getKmTraveled());
        car.setFuelType(_fuelTypeRepository.findOneById(request.getFuelTypeId()));
        car.setGearshiftType(_gearshiftTypeRepository.findOneById(request.getGearshiftTypeId()));
        _carRepository.save(car);

        Ad ad = new Ad();
        ad.setName(carModel.getCarBrand().getName() + " " + carModel.getName() + " " + carModel.getCarClass().getName());
        ad.setCar(car);
        ad.setCdw(request.isCdw());
        ad.setCreationDate(request.getCreationDate());
        ad.setLimitedDistance(request.isLimitedDistance());
        ad.setLimitedKm(request.getLimitedKm());
        ad.setSeats(request.getSeats());
        ad.setPublisher(request.getPublisherId());
        ad.setSimpleUser(request.isSimpleUser());
        _adRepository.save(ad);

        return mapAdToAdResponse(ad);
    }

    @Override
    public List<AdResponse> getAllAds() {
        List<Ad> ads = _adRepository.findAll();

        return  ads.stream()
                .map(ad -> mapAdToAdResponse(ad))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateAdById(Long id, UpdateAdRequest request) {
        Ad ad = _adRepository.findOneById(id);
        if(ad != null) {
            ad.setLimitedKm(request.getLimitedKm());
            ad.setSeats(request.getSeats());
            ad.setLimitedDistance(request.isLimitedDistance());
            ad.setCdw(request.isCdw());

            _adRepository.save(ad);
            return true;
        }
        return false;
    }

    @Override
    public List<AdResponse> getAllPublisherAds(Long id) {

        List<Ad> ads = _adRepository.findAllByPublisher(id);

        return  ads.stream()
                .map(ad -> mapAdToAdResponse(ad))
                .collect(Collectors.toList());
    }

    private AdResponse mapAdToAdResponse(Ad ad) {
        AdResponse adResponse = new AdResponse();
        if(ad.getId() != null)
            adResponse.setId(ad.getId());
        if(ad.getCar() != null) {
            adResponse.setCarId(ad.getCar().getId());
        }
        if(ad.getPublisher() != null)
            adResponse.setPublisherId(ad.getPublisher());
        if(ad.getCreationDate() != null)
            adResponse.setCreationDate(ad.getCreationDate());
        if(ad.getName() != null)
            adResponse.setName(ad.getName());
        adResponse.setLimitedKm(ad.getLimitedKm());
        adResponse.setSeats(ad.getSeats());
        adResponse.setCdw(ad.isCdw());
        adResponse.setLimitedDistance(ad.isLimitedDistance());
        adResponse.setSimpleUser(ad.isSimpleUser());


        return adResponse;
    }

    @Override
    public AdResponse getAdById(Long id) {
        Ad ad = _adRepository.findOneById(id);
        if(ad != null) {
            return mapAdToAdResponse(ad);
        }
        return null;
    }
}
