package com.rushika.billdesk.Repository;

import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCreatedBy(Admin admin);

    List<Product> findByNameContainingIgnoreCase(String keyword);
}