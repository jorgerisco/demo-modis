package com.demo.modis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "brands")
@Data
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "brand_id")
	private Long id;

	@Column(name = "description")
	private String description;

}