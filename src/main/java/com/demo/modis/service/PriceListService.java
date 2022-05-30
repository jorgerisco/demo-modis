package com.demo.modis.service;

import java.util.Date;

import com.demo.modis.dto.PriceListResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PriceListService {

	Flux<PriceListResponse> getPriceLists();

	Mono<PriceListResponse> getPriceList(Long id);

	Mono<PriceListResponse> getPriceListsByCustomSearch(Date date, Long brandId, String productCode);

}