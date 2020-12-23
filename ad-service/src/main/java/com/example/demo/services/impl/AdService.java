package com.example.demo.services.impl;

import com.example.demo.client.AuthClient;
import com.example.demo.dto.client.Agent;
import com.example.demo.dto.response.AdResponse;
import com.example.demo.dto.response.CarResponse;
import com.example.demo.entity.Ad;
import com.example.demo.repository.IAdRepository;
import com.example.demo.services.IAdService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdService implements IAdService {

    private final IAdRepository _adRepository;
    private final AuthClient _authClient;

    public AdService(IAdRepository adRepository, AuthClient authClient){
        _adRepository = adRepository;
        _authClient = authClient;
    }

    @Override
    public AdResponse getAdById(Long id) {
       /* Ad ad = _adRepository.findOneById(id);
        Agent agent = _authClient.getAgent(ad.getOwnerId());
        if(ad != null){
            AdResponse adResponse = new AdResponse();
            adResponse.setId(ad.getId());
            adResponse.setAgent(agent);
            adResponse.setLimitedDistance(ad.isLimitedDistance());
            adResponse.setLimitedKm(ad.getLimitedKm());
            adResponse.setCdw(ad.isCdw());
            adResponse.setName(ad.getCar().getCarModel().getCarBrand().getName() + " " + ad.getCar().getCarModel().getName());
            adResponse.setSeats(ad.getSeats());
            adResponse.setCarId(ad.getCar().getId());

            return adResponse;
        }*/
        return null;
    }
}
