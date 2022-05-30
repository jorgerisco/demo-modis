package com.demo.modis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.modis.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	Brand findById(long id);

}