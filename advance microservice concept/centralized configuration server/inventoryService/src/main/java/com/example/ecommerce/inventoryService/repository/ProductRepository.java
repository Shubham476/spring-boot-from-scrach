package com.example.ecommerce.inventoryService.repository;

import com.example.ecommerce.inventoryService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
