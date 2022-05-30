package com.demo.modis.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.demo.modis.dto.CurrencyRequest;
import com.demo.modis.dto.CurrencyResponse;
import com.demo.modis.model.Currency;
import com.demo.modis.repository.CurrencyRepository;
import com.demo.modis.service.CurrencyService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	private final CurrencyRepository currencyRepository;

	public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	@Override
	public Mono<CurrencyResponse> createCurrency(CurrencyRequest currency) {
		return Mono.fromCallable(() -> constructorCurrency(currency))
				.flatMap(r -> Mono.fromFuture(CompletableFuture.supplyAsync(() -> currencyRepository.save(r)))
				.map(this::constructorCurrencyResponse));
	}

	@Override
	public Mono<CurrencyResponse> updateCurrency(Long id, CurrencyRequest request) {
		return find(id)
				.map(r -> {
						r.setIsoCode(request.getIsoCode());
						r.setDescription(request.getDescription());
						return r;
					})
				.flatMap(currency -> Mono.fromFuture(CompletableFuture.supplyAsync(() -> currencyRepository.save(currency)))
				.map(this::constructorCurrencyResponse));
	}

	@Override
	public Flux<CurrencyResponse> getCurrencies() {
		return Mono.fromFuture(CompletableFuture.supplyAsync(() -> currencyRepository.findAll()))
				.flatMapMany(Flux::fromIterable)
				.map(this::constructorCurrencyResponse)
				.switchIfEmpty(Flux.empty());
	}

	@Override
	public Mono<CurrencyResponse> getCurrency(Long id) {
		return find(id)
				.map(this::constructorCurrencyResponse);
	}

	private Mono<Currency> find(Long id) {
		return Mono.fromFuture(CompletableFuture.supplyAsync(() -> currencyRepository.findById(id)))
				.flatMap(r -> r.isPresent() ? Mono.fromCallable(r::get) : Mono.empty());
	}
	
	private Currency constructorCurrency(CurrencyRequest request) {

		Currency response = new Currency();
		response.setIsoCode(request.getIsoCode());
		response.setDescription(request.getDescription());

		return response;
	}
	
	private CurrencyResponse constructorCurrencyResponse(Currency currency) {
		return CurrencyResponse.builder()
				.id(currency.getId())
				.isoCode(currency.getIsoCode())
				.description(currency.getDescription()).build();
	}

}