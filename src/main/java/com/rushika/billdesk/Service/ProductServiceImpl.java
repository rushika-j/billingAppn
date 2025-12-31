package com.rushika.billdesk.Service;

import com.rushika.billdesk.DTO.ProductRequestDTO;
import com.rushika.billdesk.DTO.ProductResponseDTO;
import com.rushika.billdesk.Mappers.ProductMapper;
import com.rushika.billdesk.Repository.AdminRepository;
import com.rushika.billdesk.Repository.ProductRepository;
import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired private  ProductRepository productRepository;
    @Autowired private AdminRepository adminRepository;
    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        // 1. Get authenticated admin's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // 2. Fetch Admin entity from DB
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));

        // 3. Map DTO to entity
        Product product = ProductMapper.toEntity(dto);

        // 4. Set the 'createdBy' field
        product.setCreatedBy(admin);

        // 5. Save product
        product = productRepository.save(product);

        // 6. Return response DTO
        return ProductMapper.toDTO(product);
    }


    @Override
    public ProductResponseDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setTaxPercentage(dto.getTaxPercentage());

        return ProductMapper.toDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
