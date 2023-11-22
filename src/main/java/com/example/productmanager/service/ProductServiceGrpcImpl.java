package com.example.productmanager.service;









import com.example.productmanager.entity.Product;
import com.example.productmanager.generatedGrpc.ProductGrpc;
import com.example.productmanager.generatedGrpc.ProductServiceGrpc;
import com.example.productmanager.repository.RepositoryProductGrpc;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceGrpcImpl extends ProductServiceGrpc.ProductServiceImplBase {

    private RepositoryProductGrpc repository;

    @Override
    public void getProduct(ProductGrpc.ProductRequest request,
                           StreamObserver<ProductGrpc.ProductResponse> responseObserver) {
        Product product = repository.findById(request.getId()).orElse(null);
        if (product == null){
            System.out.println("Продукт не найден");
            return;
        }
        ProductGrpc.ProductResponse response = ProductGrpc.ProductResponse
                .newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setAmount(product.getAmount())
                        .build();

        System.out.println("Ответ сформирован: \n" + product);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteProduct(ProductGrpc.ProductRequest request,
                              StreamObserver<ProductGrpc.DeleteProductResponse> responseObserver) {
        repository.deleteById(request.getId());
        System.out.println("Успешное удаление: " + request.getId());
        ProductGrpc.DeleteProductResponse response = ProductGrpc.DeleteProductResponse
                .newBuilder()
                .setIsDelete(true)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
