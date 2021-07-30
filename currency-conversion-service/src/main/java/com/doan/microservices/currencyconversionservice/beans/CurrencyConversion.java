package com.doan.microservices.currencyconversionservice.beans;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CurrencyConversion {

    private BigInteger id;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal conversionMultiple;
    private BigDecimal quantity;
    private BigDecimal convertedAmount;
    private String serviceEnvironment;

    public CurrencyConversion() { }

    public CurrencyConversion(BigInteger id, String fromCurrency, String toCurrency, BigDecimal quantity, BigDecimal conversionMultiple, BigDecimal convertedAmount) {
        this.id = id;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.conversionMultiple = conversionMultiple;
        this.quantity = quantity;
        this.convertedAmount = convertedAmount;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public String getServiceEnvironment() {
        return serviceEnvironment;
    }

    public void setServiceEnvironment(String serviceEnvironment) {
        this.serviceEnvironment = serviceEnvironment;
    }
}

