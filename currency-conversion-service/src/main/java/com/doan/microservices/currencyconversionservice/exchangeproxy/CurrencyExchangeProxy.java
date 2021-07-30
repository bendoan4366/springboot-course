package com.doan.microservices.currencyconversionservice.exchangeproxy;

import com.doan.microservices.currencyconversionservice.beans.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name="currency-exchange", url="localhost:8100")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String fromCurrency, @PathVariable String toCurrency);

}
