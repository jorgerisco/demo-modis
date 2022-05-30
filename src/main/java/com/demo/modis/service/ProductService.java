package com.demo.modis.service;

import com.demo.modis.dto.ProductRequest;
import com.demo.modis.dto.ProductResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	Mono<ProductResponse> createProduct(ProductRequest product);

	Mono<ProductResponse> updateProduct(Long id, ProductRequest request);

	Flux<ProductResponse> getProducts();

	Mono<ProductResponse> getProduct(Long id);

}