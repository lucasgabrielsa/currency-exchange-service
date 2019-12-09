package com.lucasdev.microservices.currencyexchangeservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasdev.microservices.currencyexchangeservice.bean.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

	Optional<ExchangeValue> findByFromAndTo(String from, String to);
	
}
