package com.demo.modis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.demo.modis.dto.CurrencyResponse;
import com.demo.modis.repository.CurrencyRepository;
import com.demo.modis.service.CurrencyService;

import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@WebFluxTest(CurrencyController.class)
public class CurrencyControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private CurrencyRepository repository;

	@MockBean
	private CurrencyService service;
	
	@Test
	public void listCurrencys() {		
		CurrencyResponse currency1 = CurrencyResponse.builder()
				.id(1L)
				.isoCode("EUR")
				.description("EUROS")
				.build();

		CurrencyResponse currency2 = CurrencyResponse.builder()
				.id(2L)
				.isoCode("USD")
				.description("DOLARES AMERICANOS")
				.build();
		
		BDDMockito.given(this.service.getCurrencies()).willReturn(Flux.just(currency1, currency2));
		
		this.webTestClient.get().uri("/currencies").accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectBodyList(CurrencyResponse.class)
				.hasSize(2)
				.contains(currency1, currency2);
	}
}