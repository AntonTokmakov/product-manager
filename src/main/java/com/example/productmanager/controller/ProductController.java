package com.example.productmanager.controller;

import com.example.productmanager.dto.ProductDto;
import com.example.productmanager.entity.Product;
import com.example.productmanager.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDto product){
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> readAll(){
        return new ResponseEntity<>(productService.productAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product){
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return HttpStatus.OK;
    }

}
