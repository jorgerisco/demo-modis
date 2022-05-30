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

import com.demo.modis.dto.CurrencyRequest;
import com.demo.modis.dto.CurrencyResponse;
import com.demo.modis.service.CurrencyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Tag(description = "API Currencies", name = "Currencies")
public class CurrencyController {

	private CurrencyService currencyService;

	public CurrencyController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	@PostMapping(value = "/currency")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(description = "Create currency", summary = "Create currency")
	public Mono<ResponseEntity<CurrencyResponse>> createCurrency(@Valid @RequestBody CurrencyRequest currency) {
		return currencyService.createCurrency(currency)
				.map(ResponseEntity::ok);
	} 

	@GetMapping(value = "/currencies")
	@Operation(description = "Get currencies", summary = "Get currencies")
	public Flux<CurrencyResponse> getCurrencies() {
		return currencyService.getCurrencies();
	}
 
	@GetMapping(value = "/currencies/{id}")
	@Operation(description = "Get currency", summary = "Get currency")
	public Mono<ResponseEntity<CurrencyResponse>> getCurrency(@PathVariable Long id) {
		return currencyService.getCurrency(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}	

	@PutMapping(value = "/currencies/{id}")
	@Operation(description = "Update currency", summary = "Update currency")
	public Mono<ResponseEntity<CurrencyResponse>> updateCurrency(@PathVariable Long id, @Valid @RequestBody CurrencyRequest currency) {
		return currencyService.updateCurrency(id, currency)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

}