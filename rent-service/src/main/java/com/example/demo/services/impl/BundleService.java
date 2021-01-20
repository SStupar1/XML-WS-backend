package com.example.demo.services.impl;

import com.example.demo.client.AdClient;
import com.example.demo.dto.client.Ad;
import com.example.demo.dto.request.ReservationRequest;
import com.example.demo.dto.response.BundleResponse;
import com.example.demo.dto.response.ReservationResponse;
import com.example.demo.entity.Bundle;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.IBundleRepository;
import com.example.demo.repository.IReservationRepository;
import com.example.demo.services.IBundleService;
import com.example.demo.util.enums.ReservationStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BundleService implements IBundleService {

    private final AdClient _adClient;
    private final IBundleRepository _bundleRepository;
    private final ReservationService _reservationService;
    private final IReservationRepository _reservationRepository;

    public BundleService(AdClient adClient, IBundleRepository bundleRepository, ReservationService reservationService, IReservationRepository reservationRepository) {
        _adClient = adClient;
        _bundleRepository = bundleRepository;
        _reservationService = reservationService;
        _reservationRepository = reservationRepository;
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
        bundle.setStatus(ReservationStatus.PENDING);
        Ad ad = _adClient.getAd(request.get(0).getAdId());
        bundle.setPublisherId(ad.getPublisher().getId());
        _bundleRepository.save(bundle);
        setBundleToNewReservations(reservations, bundle);
        return true;
    }

    private void setBundleToNewReservations(List<Reservation> reservations, Bundle bundle) {
        for(Reservation r: reservations) {
            r.setBundle(bundle);
            _reservationRepository.save(r);
        }
    }

    @Override
    public List<BundleResponse> getPublisherBundles(Long publisherId, boolean simpleUser) {
        List<BundleResponse> bundleResponses = new ArrayList<>();
        List<Bundle> allBundles = _bundleRepository.findAllByStatus(ReservationStatus.PENDING);
        for(Bundle b: allBundles){
            if(b.getPublisherId().equals(publisherId)){
                Ad ad = _adClient.getAd(b.getReservations().get(0).getAdId());
                if(ad.isSimpleUser() == simpleUser){
                    BundleResponse bundleResponse = mapBundleToBundleResponse(b);
                    bundleResponses.add(bundleResponse);
                }
            }
        }
        return bundleResponses;
    }

    @Override
    public BundleResponse approveBundle(Long id) {
        Bundle bundle = _bundleRepository.findOneById(id);
        bundle.setStatus(ReservationStatus.APPROVED);
        _bundleRepository.save(bundle);
        for(Reservation r: bundle.getReservations()){
            //njegove rezervacije
            _reservationService.approveReservation(r.getId());
        }
        return mapBundleToBundleResponse(bundle);
    }

    @Override
    public BundleResponse denyBundle(Long id) {
        Bundle bundle = _bundleRepository.findOneById(id);
        bundle.setStatus(ReservationStatus.DENIED);
        _bundleRepository.save(bundle);
        for(Reservation r: bundle.getReservations()){
            //njegove rezervacije
            _reservationService.denyReservation(r.getId());
        }
        return mapBundleToBundleResponse(bundle);
    }

    private BundleResponse mapBundleToBundleResponse(Bundle b) {
        BundleResponse bundleResponse = new BundleResponse();
        bundleResponse.setId(b.getId());
        bundleResponse.setStatus(b.getStatus());
        bundleResponse.setPublisherId(b.getPublisherId());
        List<ReservationResponse> reservationResponses = _reservationService.mapReservationsToReservationResponses(b.getReservations());
        bundleResponse.setReservations(reservationResponses);
        return  bundleResponse;
    }

    //approve and deny bundle
}
