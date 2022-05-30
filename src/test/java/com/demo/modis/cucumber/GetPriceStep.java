package com.demo.modis.cucumber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.modis.dto.PriceListResponse;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetPriceStep {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private ResponseEntity<PriceListResponse> responseEntity;

    @Then("the user receives status code 200")
    public void checkStatusCode(){
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @And("verifies that the results correspond to expected result {double}")
    public void checkResults(double expectedResult) {
    	assertThat(responseEntity.getBody()).isNotNull();
        assertEquals(responseEntity.getBody().getPrice(), expectedResult);
    }

    @When("the user call to endpoint GET with params {string}, {string} and {string}")
    public void callEndpoint(String date, String brandId, String productCode) {
    	
    	String url = "http://localhost:8082/priceListsCustomSearch?date={date}&brandId={brandId}&productCode={productCode}";

    	HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
    	
    	Map<String, String> params = new HashMap<>();
        params.put("date", date);
        params.put("brandId", brandId);
        params.put("productCode", productCode);
    	
        responseEntity = testRestTemplate.exchange(url, HttpMethod.GET, requestEntity, PriceListResponse.class, params);
    }

}