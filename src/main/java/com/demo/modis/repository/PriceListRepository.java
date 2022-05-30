package com.demo.modis.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.modis.model.PriceList;

public interface PriceListRepository extends JpaRepository<PriceList, Long> {

	PriceList findById(long id);
	
	@Query(value = "SELECT pl.* FROM price_lists pl "
			+ "INNER JOIN products p ON (pl.product_id = p.product_id) "
			+ "WHERE pl.brand_id = :brandId "
			+ "AND p.code = :productCode "
			+ "AND pl.start_date <= :date "
			+ "AND pl.end_date >= :date "
			+ "ORDER BY pl.priority DESC "
			+ "LIMIT 1",
			nativeQuery = true)
	PriceList findOneByCustomFields(Date date, long brandId, String productCode);

}