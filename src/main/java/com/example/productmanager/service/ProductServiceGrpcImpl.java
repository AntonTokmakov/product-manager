package com.example.productmanager.service;

//import com.example.productmanager.generatedGrpc.ProductGrpc;
//import com.example.productmanager.generatedGrpc.ProductServiceGrpc;
//import com.example.productmanager.repository.RepositoryProductGrpc;
//import io.grpc.Status;
//import io.grpc.StatusRuntimeException;
//import io.grpc.stub.StreamObserver;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.stereotype.Service;

import com.example.productmanager.entity.Product;
import com.example.productmanager.generateProto.ProductServiceGrpc;
import com.example.productmanager.generateProto.ProductGrpc2;
import com.example.productmanager.repository.RepositoryProduct;
import com.example.productmanager.repository.RepositoryProductGrpc;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceGrpcImpl extends ProductServiceGrpc.ProductServiceImplBase {

    private RepositoryProductGrpc repository;


    @Override
    public void getProduct(ProductGrpc2.ProductRequest request,
                           StreamObserver<ProductGrpc2.ProductResponse> responseObserver) {
        Product product = repository.findById(request.getId()).orElse(null);
        if (product == null){
            System.out.println("Продукт не найден");
            return;
        }
        ProductGrpc2.ProductResponse response = ProductGrpc2.ProductResponse
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
    public void deleteProduct(ProductGrpc2.ProductRequest request,
                              StreamObserver<ProductGrpc2.DeleteProductResponse> responseObserver) {
        repository.deleteById(request.getId());
        System.out.println("Успешное удаление: " + request.getId());
        ProductGrpc2.DeleteProductResponse response = ProductGrpc2.DeleteProductResponse
                .newBuilder()
                .setIsDelete(true)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    //    @Override
//    public void getProduct(ProductGrpc2.ProductRequest request,
//                           StreamObserver<ProductGrpc2.ProductResponse> responseObserver) {
//
//        if (request == null){
//            responseObserver.onError(error());
//        } else {
//            responseObserver.onNext(next(request.getId()));
//        }
//        responseObserver.onCompleted();
//    }
//
//    private StatusRuntimeException error(){
//        return Status.INVALID_ARGUMENT
//                .withDescription("Name.")
//                .asRuntimeException();
//    }
//
//    private ProductGrpc2.ProductResponse next(Long id){
//        ProductGrpc2.ProductResponse response = ProductGrpc2.ProductResponse
//                .newBuilder().setId(id).setName("Hello for " + id).setAmount(500).build();
//        System.out.println(response);
//        return response;
//    }
//
//    @Override
//    public void deleteProduct(ProductGrpc2.ProductRequest request, StreamObserver<ProductGrpc2.DeleteProductResponse> responseObserver) {
//        super.deleteProduct(request, responseObserver);
//    }

}
