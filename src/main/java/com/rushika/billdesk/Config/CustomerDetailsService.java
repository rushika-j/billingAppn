//package com.rushika.billdesk.Config;
//
//import com.rushika.billdesk.Repository.CustomerRepository;
//import com.rushika.billdesk.entity.Customer;
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
//public class CustomerDetailsService implements UserDetailsService {
//
//    private final CustomerRepository customerRepository;
//
//    public CustomerDetailsService(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Customer customer = customerRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
//
//        return new org.springframework.security.core.userdetails.User(
//                customer.getEmail(),
//                customer.getPassword(),
//                List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER")) // very important
//        );
//    }
//}
