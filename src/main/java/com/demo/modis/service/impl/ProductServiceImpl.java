package com.demo.modis.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.demo.modis.dto.ProductRequest;
import com.demo.modis.dto.ProductResponse;
import com.demo.modis.model.Product;
import com.demo.modis.repository.ProductRepository;
import com.demo.modis.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Mono<ProductResponse> createProduct(ProductRequest product) {
		return Mono.fromCallable(() -> constructorProduct(product))
				.flatMap(r -> Mono.fromFuture(CompletableFuture.supplyAsync(() -> productRepository.save(r)))
				.map(this::constructorProductResponse));
	}

	@Override
	public Mono<ProductResponse> updateProduct(Long id, ProductRequest request) {
		return find(id)
				.map(r -> {
						r.setCode(request.getCode());
						r.setDescription(request.getDescription());
						return r;
					})
				.flatMap(product -> Mono.fromFuture(CompletableFuture.supplyAsync(() -> productRepository.save(product)))
				.map(this::constructorProductResponse));
	}

	@Override
	public Flux<ProductResponse> getProducts() {
		return Mono.fromFuture(CompletableFuture.supplyAsync(() -> productRepository.findAll()))
				.flatMapMany(Flux::fromIterable)
				.map(this::constructorProductResponse)
				.switchIfEmpty(Flux.empty());
	}

	@Override
	public Mono<ProductResponse> getProduct(Long id) {
		return find(id)
				.map(this::constructorProductResponse);
	}
	
	private Mono<Product> find(Long id) {
		return Mono.fromFuture(CompletableFuture.supplyAsync(() -> productRepository.findById(id)))
				.flatMap(r -> r.isPresent() ? Mono.fromCallable(r::get) : Mono.empty());
	}
	
	private Product constructorProduct(ProductRequest request) {

		Product response = new Product();
		response.setCode(request.getCode());
		response.setDescription(request.getDescription());

		return response;
	}
	
	private ProductResponse constructorProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.code(product.getCode())
				.description(product.getDescription()).build();
	}

}