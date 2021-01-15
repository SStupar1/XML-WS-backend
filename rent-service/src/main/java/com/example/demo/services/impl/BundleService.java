package com.example.demo.services.impl;

import com.example.demo.client.AdClient;
import com.example.demo.dto.client.Ad;
import com.example.demo.dto.request.RequestId;
import com.example.demo.dto.request.ReservationRequest;
import com.example.demo.dto.response.BundleResponse;
import com.example.demo.dto.response.ReservationResponse;
import com.example.demo.entity.Bundle;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.IBundleRepository;
import com.example.demo.services.IBundleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BundleService implements IBundleService {

    private final AdClient _adClient;
    private final IBundleRepository _bundleRepository;
    private final ReservationService _reservationService;

    public BundleService(AdClient adClient, IBundleRepository bundleRepository, ReservationService reservationService) {
        _adClient = adClient;
        _bundleRepository = bundleRepository;
        _reservationService = reservationService;
    }


    @Override
    public boolean createBundle(List<ReservationRequest> request) {
        Bundle bundle = new Bundle();
        List<Reservation> reservations = new ArrayList<>();
        for(ReservationRequest r: request){
            Reservation reservation = _reservationService.createReservationEntity(r);
            reservation.setBundle(bundle);
            reservations.add(reservation);
        }
        bundle.setReservations(reservations);
        Ad ad = _adClient.getAd(request.get(0).getAdId());
        bundle.setPublisherId(ad.getPublisher().getId());
        _bundleRepository.save(bundle);
        setBundleToNewReservations(reservations, bundle);
        return true;
    }

    private void setBundleToNewReservations(List<Reservation> reservations, Bundle bundle) {
        for(Reservation r: reservations){
            r.setBundle(bundle);
        }
    }

    @Override
    public List<BundleResponse> getPublisherBundles(RequestId request) {
        List<BundleResponse> bundleResponses = new ArrayList<>();
        List<Bundle> allBundles = _bundleRepository.findAll();
        for(Bundle b: allBundles){
            if(b.getPublisherId().equals(request.getId())){
                BundleResponse bundleResponse = mapBundleToBundleResponse(b);
                bundleResponses.add(bundleResponse);
            }
        }
        return bundleResponses;
    }

    private BundleResponse mapBundleToBundleResponse(Bundle b) {
        BundleResponse bundleResponse = new BundleResponse();
        bundleResponse.setId(b.getId());
        bundleResponse.setPublisherId(b.getPublisherId());
        List<ReservationResponse> reservationResponses = _reservationService.mapReservationsToReservationResponses(b.getReservations());
        bundleResponse.setReservations(reservationResponses);
        return  bundleResponse;
    }


}
