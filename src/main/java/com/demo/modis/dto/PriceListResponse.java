package com.demo.modis.dto;

import com.demo.modis.model.Brand;
import com.demo.modis.model.Currency;
import com.demo.modis.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceListResponse {

	private Long id;

	private Brand brand;

	private String startDate;

	private String endDate;

	private Product product;

	private Double price;

	private Currency currency;

	private Integer priority;

}