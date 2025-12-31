package com.rushika.billdesk.Service;
import com.rushika.billdesk.DTO.CustomerRequestDTO;
import com.rushika.billdesk.DTO.CustomerResponseDTO;
import com.rushika.billdesk.entity.Customer;

import java.util.Optional;
import java.util.List;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO dto);
    List<CustomerResponseDTO> getAllCustomers();
    CustomerResponseDTO getCustomerById(Long id);
    void deleteCustomer(Long id);
    void register(CustomerRequestDTO dto);
}
