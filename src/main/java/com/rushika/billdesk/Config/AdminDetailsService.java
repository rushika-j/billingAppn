//package com.rushika.billdesk.Config;
//
//import com.rushika.billdesk.Repository.AdminRepository;
//import com.rushika.billdesk.entity.Admin;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AdminDetailsService implements UserDetailsService {
//
//    private final AdminRepository adminRepository;
//
//    public AdminDetailsService(AdminRepository adminRepository) {
//        this.adminRepository = adminRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Admin admin = adminRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
//
//        return new org.springframework.security.core.userdetails.User(
//                admin.getEmail(),
//                admin.getPassword(),
//                List.of(new SimpleGrantedAuthority("ROLE_ADMIN")) // very important
//        );
//    }
//}
