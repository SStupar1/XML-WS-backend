package com.example.demo.services.impl;

import com.example.demo.dto.request.CreateDiscountRequest;
import com.example.demo.dto.response.DiscountResponse;
import com.example.demo.dto.response.PricelistResponse;
import com.example.demo.entity.Discount;
import com.example.demo.entity.Pricelist;
import com.example.demo.repository.IDiscountRepository;
import com.example.demo.services.IDiscountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountService implements IDiscountService {
    private final IDiscountRepository _discountRepository;

    public DiscountService(IDiscountRepository discountRepository) {
        _discountRepository = discountRepository;

    }

    @Override
    public DiscountResponse getDiscountById(Long id) {
        Discount discount = _discountRepository.findOneById(id);
        if(discount != null) {
            return mapDiscountToDiscountResponse(discount);
        }
        return null;
    }

    @Override
    public List<DiscountResponse> getAllDiscounts() {
        List<Discount> discounts = _discountRepository.findAll();

        return  discounts.stream()
                .map(discount -> mapDiscountToDiscountResponse(discount))
                .collect(Collectors.toList());
    }

    public DiscountResponse mapDiscountToDiscountResponse(Discount discount) {

        DiscountResponse discountResponse = new DiscountResponse();
        if(discount.getId() != null)
            discountResponse.setId(discount.getId());
        discountResponse.setDiscount(discount.getDiscount());

        return discountResponse;
    }
}
