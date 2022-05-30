package com.demo.modis.service.impl;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.demo.modis.dto.PriceListResponse;
import com.demo.modis.model.PriceList;
import com.demo.modis.repository.PriceListRepository;
import com.demo.modis.service.PriceListService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PriceListServiceImpl implements PriceListService {

	private final PriceListRepository priceListRepository;

	public PriceListServiceImpl(PriceListRepository priceListRepository) {
		this.priceListRepository = priceListRepository;
	}

	@Override
	public Flux<PriceListResponse> getPriceLists() {
		return Mono.fromFuture(CompletableFuture.supplyAsync(() -> priceListRepository.findAll()))
				.flatMapMany(Flux::fromIterable)
				.map(this::constructorPriceListResponse)
				.switchIfEmpty(Flux.empty());
	}

	@Override
	public Mono<PriceListResponse> getPriceList(Long id) {
		return find(id)
				.map(this::constructorPriceListResponse);
	}

	@Override
	public Mono<PriceListResponse> getPriceListsByCustomSearch(Date date, Long brandId, String productCode) {
		return Mono.fromFuture(CompletableFuture.supplyAsync(() -> priceListRepository.findOneByCustomFields(date, brandId, productCode)))
				.map(this::constructorPriceListResponse);
	}

	private Mono<PriceList> find(Long id) {
		return Mono.fromFuture(CompletableFuture.supplyAsync(() -> priceListRepository.findById(id)))
				.flatMap(r -> r.isPresent() ? Mono.fromCallable(r::get) : Mono.empty());
	}

	private PriceListResponse constructorPriceListResponse(PriceList priceList) {
		return PriceListResponse.builder()
				.id(priceList.getId())
				.brand(priceList.getBrand())
				.startDate(priceList.getStartDate().toString())
				.endDate(priceList.getEndDate().toString())
				.product(priceList.getProduct())
				.price(priceList.getPrice())
				.currency(priceList.getCurrency())
				.priority(priceList.getPriority()).build();
	}

}