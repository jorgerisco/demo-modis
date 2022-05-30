package com.demo.modis.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.modis.dto.PriceListResponse;
import com.demo.modis.service.PriceListService;
import com.demo.modis.util.Utils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Tag(description = "API PriceLists", name = "PriceLists")
public class PriceListController {

	private PriceListService priceListService;

	public PriceListController(PriceListService priceListService) {
		this.priceListService = priceListService;
	}

	@GetMapping(value = "/priceLists")
	@Operation(description = "Get priceLists", summary = "Get priceLists")
	public Flux<PriceListResponse> getPriceLists() {
		return priceListService.getPriceLists();
	}
	
	@GetMapping(value = "/priceListsCustomSearch")
	@Operation(description = "Get priceLists by custom search", summary = "Get priceLists by custom search")
	public Mono<ResponseEntity<PriceListResponse>> getPriceListsCustomSearch(
			@RequestParam(value = "date", required = true) String date,
            @RequestParam(value = "brandId", required = true) String brandId,
            @RequestParam(value = "productCode", required = true) String productCode) {
		return priceListService.getPriceListsByCustomSearch(Utils.parseTimestamp(date), Long.valueOf(brandId), productCode)
				.map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.notFound().build());
	}
 
	@GetMapping(value = "/priceLists/{id}")
	@Operation(description = "Get priceList", summary = "Get priceList")
	public Mono<ResponseEntity<PriceListResponse>> getPriceList(@PathVariable Long id) {
		return priceListService.getPriceList(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

}