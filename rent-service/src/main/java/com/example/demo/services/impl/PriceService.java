package com.example.demo.services.impl;

import com.example.demo.dto.response.PriceResponse;
import com.example.demo.services.IPriceService;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

@Service
public class PriceService implements IPriceService {

    @Override
    public PriceResponse calculateStartingPricePerAd(double priceCDW,boolean availableCDW, double pricePerDay, double discount, String fromDateString, String toDateString) {
        LocalDate fromDate = LocalDate.parse(fromDateString);
        LocalDate toDate = LocalDate.parse(toDateString);
        long noOfDaysBetween = ChronoUnit.DAYS.between(fromDate, toDate);
        //ako rentira za jedan dan noOfDaysBetween ce biti 0 zato odradimo ++
        noOfDaysBetween++;

        double price = 0;
        if(availableCDW){
            price += priceCDW;
        }
        price += noOfDaysBetween*pricePerDay;
        if(discount!=0){
            price = (price*discount)/100;
        }

        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setPrice(price);
        return priceResponse;
    }
}
