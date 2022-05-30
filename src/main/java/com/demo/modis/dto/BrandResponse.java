package com.demo.modis.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
public class BrandResponse {

	private Long id;

	private String description;

}