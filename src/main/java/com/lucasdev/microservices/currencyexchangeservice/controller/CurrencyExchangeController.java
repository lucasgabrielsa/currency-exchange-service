package com.lucasdev.microservices.currencyexchangeservice.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lucasdev.microservices.currencyexchangeservice.bean.ExchangeValue;
import com.lucasdev.microservices.currencyexchangeservice.exception.NotFoundException;
import com.lucasdev.microservices.currencyexchangeservice.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		Optional<ExchangeValue> exchangeValueOpt = exchangeValueRepository.findByFromAndTo(from, to);
		if(!exchangeValueOpt.isPresent()) {
			throw new NotFoundException(String.format("Not Found Exchange values from %s to %S", from, to));
		}
		ExchangeValue exchangeValue = exchangeValueOpt.get();
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		logger.info("{}", exchangeValue);
		
		return exchangeValue;
	}

}
