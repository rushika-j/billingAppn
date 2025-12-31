package com.rushika.billdesk.Service;

import com.rushika.billdesk.DTO.AdminLoginDTO;
import com.rushika.billdesk.DTO.AdminRequestDTO;
import com.rushika.billdesk.DTO.AdminResponseDTO;
import com.rushika.billdesk.entity.Admin;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    AdminResponseDTO createAdmin(AdminRequestDTO dto);
    List<AdminResponseDTO> getAllAdmins();
    AdminResponseDTO getAdminById(Long id);
    void deleteAdmin(Long id);
    void register(AdminRequestDTO dto);
    Optional<Admin> findByEmail(String email);
    Optional<Admin> findById(Long id);
    boolean login(AdminLoginDTO dto, HttpServletRequest request);
}
