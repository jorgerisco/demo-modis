package com.demo.modis.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.modis.dto.ProductRequest;
import com.demo.modis.dto.ProductResponse;
import com.demo.modis.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Tag(description = "API Products", name = "Products")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping(value = "/product")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(description = "Create product", summary = "Create product")
	public Mono<ResponseEntity<ProductResponse>> createProduct(@Valid @RequestBody ProductRequest product) {
		return productService.createProduct(product)
				.map(ResponseEntity::ok);
	} 

	@GetMapping(value = "/products")
	@Operation(description = "Get products", summary = "Get products")
	public Flux<ProductResponse> getProducts() {
		return productService.getProducts();
	}
 
	@GetMapping(value = "/products/{id}")
	@Operation(description = "Get product", summary = "Get product")
	public Mono<ResponseEntity<ProductResponse>> getProduct(@PathVariable Long id) {
		return productService.getProduct(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}	

	@PutMapping(value = "/products/{id}")
	@Operation(description = "Update product", summary = "Update product")
	public Mono<ResponseEntity<ProductResponse>> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest product) {
		return productService.updateProduct(id, product)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

}