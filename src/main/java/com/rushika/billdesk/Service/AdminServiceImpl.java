package com.rushika.billdesk.Service;


import com.rushika.billdesk.DTO.AdminLoginDTO;
import com.rushika.billdesk.DTO.AdminRequestDTO;
import com.rushika.billdesk.DTO.AdminResponseDTO;
import com.rushika.billdesk.Mappers.AdminMapper;
import com.rushika.billdesk.Repository.AdminRepository;
import com.rushika.billdesk.entity.Admin;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

   @Autowired
   private AuthenticationManager authenticationManager;
   @Autowired
   private AdminRepository adminRepository;

   @Autowired
    PasswordEncoder passwordEncoder;

   @Autowired AdminMapper adminMapper;


    @Override
    public void register(AdminRequestDTO dto) {
        Admin admin = new Admin();
        admin.setEmail(dto.getEmail());
        admin.setName(dto.getName());
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        adminRepository.save(admin);
    }

    public boolean login(AdminLoginDTO dto, HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // manually create session
            request.getSession(true);

            return true;
        } catch (AuthenticationException ex) {
            return false;
        }


    }


    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    public AdminResponseDTO createAdmin(AdminRequestDTO dto) {
        Admin admin = new Admin();
        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        admin.setRole("ADMIN"); // 👈 Important for Spring Security

        admin = adminRepository.save(admin);
        return adminMapper.toDTO(admin);
    }


    @Override
    public List<AdminResponseDTO> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminResponseDTO getAdminById(Long id) {
        return adminRepository.findById(id)
                .map(adminMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }
    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
    @Override
    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

}
