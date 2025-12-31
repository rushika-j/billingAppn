package com.rushika.billdesk.Mappers;


import com.rushika.billdesk.DTO.CustomerRequestDTO;
import com.rushika.billdesk.DTO.CustomerResponseDTO;
import com.rushika.billdesk.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setPassword(dto.getPassword());
        customer.setRole(dto.getRole()); // <-- added
        return customer;
    }

    public static CustomerResponseDTO toDTO(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getId());
        dto.setEmail(customer.getEmail());
        dto.setPassword(customer.getPassword());
        dto.setRole(customer.getRole()); // <-- added
        return dto;
    }
}
