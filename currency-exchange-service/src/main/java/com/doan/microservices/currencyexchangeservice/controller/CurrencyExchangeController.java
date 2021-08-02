package com.doan.microservices.currencyexchangeservice.controller;

import com.doan.microservices.currencyexchangeservice.beans.CurrencyExchange;
import com.doan.microservices.currencyexchangeservice.repositories.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private CurrencyExchangeRepository repository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String fromCurrency, @PathVariable String toCurrency) {
        CurrencyExchange currencyExchange = repository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
        if(currencyExchange == null){
            throw new RuntimeException("Cannot find exchange rate between " + fromCurrency + ":" + toCurrency);
        }

        String port = environment.getProperty("local.server.port");
        currencyExchange.setServiceEnvironment(port);

        logger.info("retrieveExchangeValue called with {} to  {}", fromCurrency, toCurrency);

        return currencyExchange;
    }
}
