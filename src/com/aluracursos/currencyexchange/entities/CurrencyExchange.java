package com.aluracursos.currencyexchange.entities;

public class CurrencyExchange {

    private Currency baseCurrency;            //Moneda base
    private Currency targetCurrency;          //Moneda objetivo
    private Double conversionRate;            //Tipo de cambio
    private Double amountToConvert;           //Importe a convertir
    private Double conversionResult;          //Resultado de la conversión

    //Constructores
    public CurrencyExchange() {
    }

    public CurrencyExchange(Currency baseCurrency, Currency targetCurrency, Double conversionRate,
                            Double amountToConvert, Double conversionResult) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.conversionRate = conversionRate;
        this.amountToConvert = amountToConvert;
        this.conversionResult = conversionResult;
    }

    //Getters y Setters
    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public Double getAmountToConvert() {
        return amountToConvert;
    }

    public Double getConversionResult() {
        return conversionResult;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public void setAmountToConvert(Double amountToConvert) {
        this.amountToConvert = amountToConvert;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public void setConversionResult(Double conversionResult) {
        this.conversionResult = conversionResult;
    }

    //toString
    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "baseCurrency=" + baseCurrency +
                ", targetCurrency=" + targetCurrency +
                ", conversionRate=" + conversionRate +
                ", amountToConvert=" + amountToConvert +
                ", conversionResult=" + conversionResult +
                '}';
    }
}
