package com.doan.microservices.currencyexchangeservice.repositories;

import com.doan.microservices.currencyexchangeservice.beans.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    CurrencyExchange findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);

}
