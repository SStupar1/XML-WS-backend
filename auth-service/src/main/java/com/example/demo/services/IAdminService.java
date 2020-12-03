package com.example.demo.services;

import com.example.demo.dto.request.UpdateAdminRequest;
import com.example.demo.dto.response.AdminResponse;

public interface IAdminService {

    AdminResponse getAdminById(Long id);

    void updateAdminById(Long id, UpdateAdminRequest request);
}
