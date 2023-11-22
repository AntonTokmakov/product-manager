package com.example.productmanager.repository;

import com.example.productmanager.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryProductGrpc extends JpaRepository<Product, Long> {
}
