package com.example.demo.services;

import com.example.demo.dto.request.RequestId;
import com.example.demo.dto.request.ReservationRequest;
import com.example.demo.dto.response.BundleResponse;

import java.util.List;

public interface IBundleService {
    boolean createBundle(List<ReservationRequest> request);

    List<BundleResponse> getPublisherBundles(RequestId request);
}
