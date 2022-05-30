package com.demo.modis.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "price_lists")
@Data
public class PriceList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "price_list_id")
	private Long id;

	@ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
	private Brand brand;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "price")
	private Double price;

	@ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
	private Currency currency;

	@Column(name = "priority")
	private Integer priority;

}