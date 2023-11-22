package com.example.productmanager.service;


import com.example.productmanager.dto.ProductDto;
import com.example.productmanager.entity.Product;
import com.example.productmanager.repository.RepositoryProduct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private RepositoryProduct repositoryProduct;

    public Product createProduct(ProductDto dto){
        return repositoryProduct.save(Product
                .builder()
                .name(dto.getName())
                .amount(dto.getAmount())
                .build());
    }

    public List<Product> productAll(){
        return repositoryProduct.findAll();
    }

    public Product updateProduct(Product product){
        return repositoryProduct.save(product);
    }

    public void deleteProduct(Long id){
        repositoryProduct.deleteById(id);
    }


}
