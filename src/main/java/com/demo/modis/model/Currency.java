package com.demo.modis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "currencies")
@Data
public class Currency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "currency_id")
	private Long id;

	@Column(name = "iso_code")
	private String isoCode;

	@Column(name = "description")
	private String description;

}