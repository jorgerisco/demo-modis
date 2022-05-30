package com.demo.modis.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ProductRequest {

	@NotEmpty(message = "Please, enter a code.")
	private String code;

	@NotEmpty(message = "Please, enter a description.")
	private String description;

}