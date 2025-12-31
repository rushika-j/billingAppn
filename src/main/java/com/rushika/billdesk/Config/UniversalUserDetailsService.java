package com.rushika.billdesk.Config;

import com.rushika.billdesk.Repository.AdminRepository;
import com.rushika.billdesk.Repository.CustomerRepository;
import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Customer;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;




import java.util.List;
import java.util.Optional;

@Service
public class UniversalUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;

    public UniversalUserDetailsService(AdminRepository adminRepository, CustomerRepository customerRepository) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
    }


@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<Admin> admin = adminRepository.findByEmail(email);
    if (admin.isPresent()) {
        Admin a = admin.get();
        String role = a.getRole(); // e.g. "ADMIN"
        return new org.springframework.security.core.userdetails.User(
                a.getEmail(),
                a.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );
    }

    Optional<Customer> customer = customerRepository.findByEmail(email);
    if (customer.isPresent()) {
        Customer c = customer.get();
        String role = c.getRole(); // Get role from entity (e.g., "CUSTOMER")
        return new org.springframework.security.core.userdetails.User(
                c.getEmail(),
                c.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );
    }

    throw new UsernameNotFoundException("User not found with email: " + email);
}

}
