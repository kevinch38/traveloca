package com.enigma.traveloca.service;

import com.enigma.traveloca.dto.request.create.CreateAdminRequest;
import com.enigma.traveloca.dto.request.update.UpdateAdminRequest;
import com.enigma.traveloca.dto.response.AdminResponse;

import java.util.List;
public interface AdminService {
    AdminResponse save(CreateAdminRequest request);
    List<AdminResponse> findAll();
    AdminResponse findById(String id);
    void deleteById(String id);
    AdminResponse update(UpdateAdminRequest request);
}
