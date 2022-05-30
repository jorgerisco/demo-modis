package com.demo.modis.service;

import com.demo.modis.dto.BrandRequest;
import com.demo.modis.dto.BrandResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BrandService {

	Mono<BrandResponse> createBrand(BrandRequest brand);

	Mono<BrandResponse> updateBrand(Long id, BrandRequest request);

	Flux<BrandResponse> getBrands();

	Mono<BrandResponse> getBrand(Long id);

}