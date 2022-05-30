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

import com.demo.modis.dto.BrandResponse;
import com.demo.modis.repository.BrandRepository;
import com.demo.modis.service.BrandService;

import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@WebFluxTest(BrandController.class)
public class BrandControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private BrandRepository repository;

	@MockBean
	private BrandService service;
	
	@Test
	public void listBrands() {		
		BrandResponse brand1 = BrandResponse.builder()
				.id(1L)
				.description("Brand 1")
				.build();

		BrandResponse brand2 = BrandResponse.builder()
				.id(2L)
				.description("Brand 2")
				.build();
		
		BDDMockito.given(this.service.getBrands()).willReturn(Flux.just(brand1, brand2));
		
		this.webTestClient.get().uri("/brands").accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectBodyList(BrandResponse.class)
				.hasSize(2)
				.contains(brand1, brand2);
	}
}