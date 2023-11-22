package com.example.productmanager;

import com.example.productmanager.service.ProductServiceGrpcImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ProductManagerApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(ProductManagerApplication.class, args);

		Server server = ServerBuilder
				.forPort(8088)
				.addService(new ProductServiceGrpcImpl())
				.build();
		server.start();

		System.out.println("Сервер запущен");

		server.awaitTermination();

	}

}
