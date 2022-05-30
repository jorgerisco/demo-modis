package com.demo.modis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.modis.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findById(long id);

}