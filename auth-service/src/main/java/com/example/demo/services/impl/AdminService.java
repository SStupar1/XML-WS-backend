package com.example.demo.services.impl;

import com.example.demo.dto.request.UpdateAdminRequest;
import com.example.demo.dto.response.AdminResponse;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Agent;
import com.example.demo.repository.IAdminRepository;
import com.example.demo.services.IAdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {

    private final IAdminRepository _adminRepository;


    public AdminService(IAdminRepository adminRepository) {
        _adminRepository = adminRepository;
    }


    public AdminResponse getAdminById(Long id) {
        Admin admin = _adminRepository.findOneById(id);
        if(admin != null){
            return mapAdminToResponse(admin);
        }
        else{
            return null;
        }
    }

    @Override
    public void updateAdminById(Long id, UpdateAdminRequest request) {
        Admin admin = _adminRepository.findOneById(id);
        if(request.getFirstName() != null)
            admin.setFirstName(request.getFirstName());
        if(request.getLastName() != null)
            admin.setLastName(request.getLastName());

        _adminRepository.save(admin);
    }

    private AdminResponse mapAdminToResponse(Admin admin){

        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setFirstName(admin.getFirstName());
        adminResponse.setLastName(admin.getLastName());

        return adminResponse;
    }
}
