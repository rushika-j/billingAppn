package com.rushika.billdesk.Mappers;

import com.rushika.billdesk.DTO.AdminRequestDTO;
import com.rushika.billdesk.DTO.AdminResponseDTO;
import com.rushika.billdesk.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;




import com.rushika.billdesk.DTO.AdminRequestDTO;
import com.rushika.billdesk.DTO.AdminResponseDTO;
import com.rushika.billdesk.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
public class AdminMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Map AdminRequestDTO -> Admin Entity
    public Admin toEntity(AdminRequestDTO dto) {
        Admin admin = new Admin();
        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        admin.setRole("ROLE_ADMIN"); // Ensure role is set
        return admin;
    }

    // Map Admin Entity -> AdminResponseDTO
    public AdminResponseDTO toDTO(Admin admin) {
        AdminResponseDTO dto = new AdminResponseDTO();
        dto.setId(admin.getId());
        dto.setName(admin.getName());
        dto.setEmail(admin.getEmail());
        dto.setRole(admin.getRole());
        return dto;
    }
}
