package com.demo.modis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.modis.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	Currency findById(long id);

}