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

import com.demo.modis.dto.ProductResponse;
import com.demo.modis.repository.ProductRepository;
import com.demo.modis.service.ProductService;

import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private ProductRepository repository;

	@MockBean
	private ProductService service;
	
	@Test
	public void listProducts() {		
		ProductResponse producto1 = ProductResponse.builder()
				.id(1L)
				.code("35455")
				.description("Producto 1")
				.build();

		ProductResponse producto2 = ProductResponse.builder()
				.id(2L)
				.code("35456")
				.description("Producto 2")
				.build();
		
		BDDMockito.given(this.service.getProducts()).willReturn(Flux.just(producto1, producto2));
		
		this.webTestClient.get().uri("/products").accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectBodyList(ProductResponse.class)
				.hasSize(2)
				.contains(producto1, producto2);
	}
}