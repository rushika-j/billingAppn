package com.rushika.billdesk.Mappers;


import com.rushika.billdesk.DTO.ProductRequestDTO;
import com.rushika.billdesk.DTO.ProductResponseDTO;
import com.rushika.billdesk.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setTaxPercentage(dto.getTaxPercentage());
        return product;
    }

    public static ProductResponseDTO toDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setTaxPercentage(product.getTaxPercentage());
        return dto;
    }
}
