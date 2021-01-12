package com.example.demo.services.impl;

import com.example.demo.client.AuthClient;
import com.example.demo.dto.client.Agent;
import com.example.demo.dto.client.SimpleUser;
import com.example.demo.dto.request.RateAdRequest;
import com.example.demo.dto.response.AverageRatingResponse;
import com.example.demo.dto.response.RatingResponse;
import com.example.demo.entity.Ad;
import com.example.demo.entity.Rating;
import com.example.demo.repository.IAdRepository;
import com.example.demo.repository.IRatingRepository;
import com.example.demo.services.IRatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService implements IRatingService {
    private final IRatingRepository _ratingRepository;
    private final AuthClient _authClient;
    private final IAdRepository _adRepository;

    public RatingService(IRatingRepository ratingRepository, AuthClient authClient, IAdRepository adRepository) {
        _ratingRepository = ratingRepository;
        _authClient = authClient;
        _adRepository = adRepository;
    }

    @Override
    public List<RatingResponse> getAllRatingsByAd(Long id) {
        List<Rating> ratings = _ratingRepository.findAllByAdId(id);
        return ratings.stream()
                .map(rating -> mapRatingToRatingResponse(rating))
                .collect(Collectors.toList());
    }

    @Override
    public RatingResponse rateAd(RateAdRequest request) {
        SimpleUser simpleUser = _authClient.getSimpleUser(request.getSimpleUserId());
        Ad ad = _adRepository.findOneById(request.getAdId());

        Rating rating = new Rating();
        rating.setGrade(request.getGrade());
        rating.setSimpleUser(simpleUser.getId());
        rating.setAd(ad);
        Rating savedRating = _ratingRepository.save(rating);
        ad.getRatings().add(savedRating);
        _adRepository.save(ad);
        return mapRatingToRatingResponse(savedRating);
    }

    @Override
    public AverageRatingResponse getAverageRatingByAd(Long id) {
        List<Rating> ratings = _ratingRepository.findAllByAdId(id);
        double sum = 0;
        double counter = 0;
        for(Rating r: ratings){
            sum += Double.valueOf(r.getGrade());
            counter++;
        }
        AverageRatingResponse response = new AverageRatingResponse();
        Agent agent = _authClient.getAgent(_adRepository.findOneById(id).getPublisher());
        response.setAgentName(agent.getName());
        response.setCarModelName(_adRepository.findOneById(id).getCar().getCarModel().getName());
        if(counter == 0){
            response.setAverageRating(0);
            return response;
        }
        response.setAverageRating(sum / counter);
        return response;
    }

    private RatingResponse mapRatingToRatingResponse(Rating rating){
        RatingResponse response = new RatingResponse();
        Agent agent = _authClient.getAgent(rating.getAd().getPublisher());
        response.setAgentName(agent.getName());
        response.setGrade(rating.getGrade());
        response.setRatingId(rating.getId());
        SimpleUser simpleUser = _authClient.getSimpleUser(rating.getSimpleUser());
        response.setCustomerFirstName(simpleUser.getFirstName());
        response.setCustomerLastName(simpleUser.getLastName());
        response.setCarBrandName(rating.getAd().getCar().getCarModel().getCarBrand().getName());
        response.setCarModelName(rating.getAd().getCar().getCarModel().getName());
        return response;
    }
}
