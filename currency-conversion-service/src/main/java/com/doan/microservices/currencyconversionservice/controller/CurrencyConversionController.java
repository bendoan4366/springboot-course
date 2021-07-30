package com.doan.microservices.currencyconversionservice.controller;

import com.doan.microservices.currencyconversionservice.beans.CurrencyConversion;
import com.doan.microservices.currencyconversionservice.exchangeproxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy exchangeProxy;

    //hardcoded conversion with RestTemplate implementation to call exchange service
    @GetMapping("/convert-currency/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String fromCurrency, @PathVariable String toCurrency, @PathVariable BigDecimal quantity) {

        HashMap<String, String> uriVariables= new HashMap<>();
        uriVariables.put("from", fromCurrency);
        uriVariables.put("to", toCurrency);

        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8100/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversionResponse = responseEntity.getBody();

        BigInteger conversionResponseId = currencyConversionResponse.getId();
        BigDecimal conversionMultiple = currencyConversionResponse.getConversionMultiple();
        BigDecimal finalConvertedAmount = quantity.multiply(conversionMultiple);
        String exchangeServer = currencyConversionResponse.getServiceEnvironment();

        CurrencyConversion finalCurrencyConversion = new CurrencyConversion(conversionResponseId, fromCurrency, toCurrency, quantity, conversionMultiple, finalConvertedAmount);
        finalCurrencyConversion.setServiceEnvironment(exchangeServer);

        return finalCurrencyConversion;
    }

    //conversion implementation with feign
    @GetMapping("/convert-currency/proxy/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionUsingProxy(@PathVariable String fromCurrency, @PathVariable String toCurrency, @PathVariable BigDecimal quantity) {

       CurrencyConversion currencyConversionResponse = exchangeProxy.retrieveExchangeValue(fromCurrency, toCurrency);

        BigInteger conversionResponseId = currencyConversionResponse.getId();
        BigDecimal conversionMultiple = currencyConversionResponse.getConversionMultiple();
        BigDecimal finalConvertedAmount = quantity.multiply(conversionMultiple);
        String exchangeServer = currencyConversionResponse.getServiceEnvironment();

        CurrencyConversion finalCurrencyConversion = new CurrencyConversion(conversionResponseId, fromCurrency, toCurrency, quantity, conversionMultiple, finalConvertedAmount);
        finalCurrencyConversion.setServiceEnvironment(exchangeServer);
        return finalCurrencyConversion;
    }

}
