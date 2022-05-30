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

import com.demo.modis.dto.BrandRequest;
import com.demo.modis.dto.BrandResponse;
import com.demo.modis.service.BrandService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Tag(description = "API Brands", name = "Brands")
public class BrandController {

	private BrandService brandService;

	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	@PostMapping(value = "/brand")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(description = "Create brand", summary = "Create brand")
	public Mono<ResponseEntity<BrandResponse>> createBrand(@Valid @RequestBody BrandRequest brand) {
		return brandService.createBrand(brand)
				.map(ResponseEntity::ok);
	} 

	@GetMapping(value = "/brands")
	@Operation(description = "Get brands", summary = "Get brands")
	public Flux<BrandResponse> getBrands() {
		return brandService.getBrands();
	}
 
	@GetMapping(value = "/brands/{id}")
	@Operation(description = "Get brand", summary = "Get brand")
	public Mono<ResponseEntity<BrandResponse>> getBrand(@PathVariable Long id) {
		return brandService.getBrand(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}	

	@PutMapping(value = "/brands/{id}")
	@Operation(description = "Update brand", summary = "Update brand")
	public Mono<ResponseEntity<BrandResponse>> updateBrand(@PathVariable Long id, @Valid @RequestBody BrandRequest brand) {
		return brandService.updateBrand(id, brand)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

}