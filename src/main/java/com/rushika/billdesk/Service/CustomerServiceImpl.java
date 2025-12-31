package com.rushika.billdesk.Service;

import com.rushika.billdesk.DTO.CustomerRequestDTO;
import com.rushika.billdesk.DTO.CustomerResponseDTO;
import com.rushika.billdesk.Mappers.CustomerMapper;
import com.rushika.billdesk.Repository.AdminRepository;
import com.rushika.billdesk.Repository.CustomerRepository;
import com.rushika.billdesk.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.Optional;
import java.util.List;

@Service

@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  @Autowired  private  CustomerRepository customerRepository;
  @Autowired
    PasswordEncoder passwordEncoder;
  @Autowired
   private AdminRepository adminRepository;
    @Override
    public void register(CustomerRequestDTO dto) {
        Customer customer = new Customer();
        customer.setEmail(dto.getEmail());
        customer.setName(dto.getName());
        customer.setPhone(dto.getPhone());
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));

        // ✅ SET ROLE
        customer.setRole("CUSTOMER");

        Customer saved = customerRepository.save(customer);
    }

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {
        Customer customer = CustomerMapper.toEntity(dto);
        customer = customerRepository.save(customer);
        return CustomerMapper.toDTO(customer);
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
