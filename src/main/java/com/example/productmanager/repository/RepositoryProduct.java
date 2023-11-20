package com.example.productmanager.repository;

import com.example.productmanager.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProduct extends JpaRepository<Product, Long> {
}
