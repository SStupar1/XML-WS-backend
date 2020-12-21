package com.example.demo.services;

import com.example.demo.dto.response.AdResponse;

public interface IAdService {
    AdResponse getAdById(Long id);
}
