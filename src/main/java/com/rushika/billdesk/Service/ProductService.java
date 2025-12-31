package com.rushika.billdesk.Service;

import com.rushika.billdesk.DTO.ProductRequestDTO;
import com.rushika.billdesk.DTO.ProductResponseDTO;
import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Product;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO dto);
    ProductResponseDTO getProductById(Long id);
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto);
    void deleteProduct(Long id);
}
