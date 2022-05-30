package com.demo.modis.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CurrencyRequest {

	@NotEmpty(message = "Please, enter a iso code.")
	private String isoCode;

	@NotEmpty(message = "Please, enter a description.")
	private String description;

}