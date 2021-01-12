package com.example.demo.services.impl;

import com.example.demo.dto.request.AddDiscountRequest;
import com.example.demo.dto.request.CreatePricelistRequest;
import com.example.demo.dto.response.DiscountResponse;
import com.example.demo.dto.response.PricelistResponse;
import com.example.demo.entity.Discount;
import com.example.demo.entity.Pricelist;
import com.example.demo.repository.IDiscountRepository;
import com.example.demo.repository.IPricelistRepository;
import com.example.demo.services.IPricelistService;
import org.springframework.stereotype.Service;

@Service
public class PricelistService implements IPricelistService {
    private final IPricelistRepository _pricelistRepository;
    private final IDiscountRepository _discountRepository;

    public PricelistService(IPricelistRepository pricelistRepository, IDiscountRepository discountRepository) {
        _pricelistRepository = pricelistRepository;
        _discountRepository = discountRepository;
    }

    @Override
    public PricelistResponse getPricelistById(Long id) {
        Pricelist pricelist = _pricelistRepository.findOneById(id);
        if(pricelist != null) {
            return mapPricelistToPricelistResponse(pricelist);
        }
        return null;
    }

    @Override
    public PricelistResponse createPricelist(CreatePricelistRequest request) {
        Pricelist pricelist = new Pricelist();
        pricelist.setPriceCDW(request.getPriceCDW());
        pricelist.setPricePerDay(request.getPricePerDay());
        pricelist.setPricePerKilometer(request.getPricePerKilometer());

        _pricelistRepository.save(pricelist);

        return mapPricelistToPricelistResponse(pricelist);
    }

    @Override
    public PricelistResponse addDiscountToPricelist(AddDiscountRequest request) {
        Pricelist pricelist = _pricelistRepository.findOneById(request.getPricelistId());
        Discount discount = _discountRepository.findOneById(request.getDiscountId());
        pricelist.setDiscount(discount);
            return mapPricelistToPricelistResponse(pricelist);
    }

    public PricelistResponse mapPricelistToPricelistResponse(Pricelist pricelist) {
        PricelistResponse pricelistResponse = new PricelistResponse();
        if(pricelist.getId() != null)
            pricelistResponse.setId(pricelist.getId());

        pricelistResponse.setPricePerDay(pricelist.getPricePerDay());
        pricelistResponse.setPriceCDW(pricelist.getPriceCDW());
        pricelistResponse.setPricePerKilometer(pricelist.getPricePerKilometer());

        return pricelistResponse;
    }
}
