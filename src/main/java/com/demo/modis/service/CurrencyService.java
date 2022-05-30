package com.demo.modis.service;

import com.demo.modis.dto.CurrencyRequest;
import com.demo.modis.dto.CurrencyResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrencyService {

	Mono<CurrencyResponse> createCurrency(CurrencyRequest currency);

	Mono<CurrencyResponse> updateCurrency(Long id, CurrencyRequest request);

	Flux<CurrencyResponse> getCurrencies();

	Mono<CurrencyResponse> getCurrency(Long id);

}