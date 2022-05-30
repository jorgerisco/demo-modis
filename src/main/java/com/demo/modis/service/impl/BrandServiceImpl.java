package com.demo.modis.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.demo.modis.dto.BrandRequest;
import com.demo.modis.dto.BrandResponse;
import com.demo.modis.model.Brand;
import com.demo.modis.repository.BrandRepository;
import com.demo.modis.service.BrandService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BrandServiceImpl implements BrandService {

	private final BrandRepository brandRepository;

	public BrandServiceImpl(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

	@Override
	public Mono<BrandResponse> createBrand(BrandRequest brand) {
		return Mono.fromCallable(() -> constructorBrand(brand))
				.flatMap(r -> Mono.fromFuture(CompletableFuture.supplyAsync(() -> brandRepository.save(r)))
				.map(this::constructorBrandResponse));
	}

	@Override
	public Mono<BrandResponse> updateBrand(Long id, BrandRequest request) {
		return find(id)
				.map(r -> {
						r.setDescription(request.getDescription());
						return r;
					})
				.flatMap(brand -> Mono.fromFuture(CompletableFuture.supplyAsync(() -> brandRepository.save(brand)))
				.map(this::constructorBrandResponse));
	}

	@Override
	public Flux<BrandResponse> getBrands() {
		return Mono.fromFuture(CompletableFuture.supplyAsync(() -> brandRepository.findAll()))
				.flatMapMany(Flux::fromIterable)
				.map(this::constructorBrandResponse)
				.switchIfEmpty(Flux.empty());
	}

	@Override
	public Mono<BrandResponse> getBrand(Long id) {
		return find(id)
				.map(this::constructorBrandResponse);
	}
	
	private Mono<Brand> find(Long id) {
		return Mono.fromFuture(CompletableFuture.supplyAsync(() -> brandRepository.findById(id)))
				.flatMap(r -> r.isPresent() ? Mono.fromCallable(r::get) : Mono.empty());
	}
	
	private Brand constructorBrand(BrandRequest request) {

		Brand response = new Brand();
		response.setDescription(request.getDescription());

		return response;
	}
	
	private BrandResponse constructorBrandResponse(Brand brand) {
		return BrandResponse.builder()
				.id(brand.getId())
				.description(brand.getDescription()).build();
	}

}