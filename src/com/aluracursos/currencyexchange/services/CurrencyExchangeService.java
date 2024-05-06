package com.aluracursos.currencyexchange.services;

import com.aluracursos.currencyexchange.dtos.CurrencyExchangeDTO;
import com.aluracursos.currencyexchange.dtos.SupportedCurrenciesDTO;
import com.aluracursos.currencyexchange.entities.Currency;
import com.aluracursos.currencyexchange.entities.CurrencyExchange;

import java.util.*;

public class CurrencyExchangeService {

    private Scanner read = new Scanner(System.in).useDelimiter("\n");
    private List<CurrencyExchange> currencyExchangeList = new ArrayList<>();
    private Set<String> validCurrencyCodesSet = new HashSet<>();  //códigos ISO admitidos

    public CurrencyExchangeService() {  //Constructor invocado desde el Main cuando inicia el programa
        updateSupportedCurrencies();    //Actualiza el listado de monedas admitidas (código ISO)
    }


    //Opción 1: solicitud de listado de monedas admitidas por la aplicación
    public Map<String, String> requestSupportedCurrencies() {
        //Solicitud de API - retorna DTO
        SupportedCurrenciesDTO supportedCurrenciesDTO = ExchangeRateAPI.supportedCurrenciesRequest();
        //Retornar los pares clave-valor (ISO,nombre) extraídos del DTO
        return supportedCurrenciesDTO.supportedCurrencies();
    }

    //Opción 2: solicitud de conversión entre divisas
    public CurrencyExchange requestCurrencyExchange(String baseCode, String targetCode, Double amountToConvert) {

        CurrencyExchange currencyExchange = createCurrencyExchange(  //método privado
                baseCode,
                targetCode,
                amountToConvert
        );
        //Agregar la operación de conversión al historial
        currencyExchangeList.add(currencyExchange);
        return currencyExchange;
    }

    //Opción 3: solicitud del historial de conversiones
    public List<CurrencyExchange> requestCurrenciesExchangesHistory() {
        return currencyExchangeList;
    }

    //Métodos privados
    private CurrencyExchange createCurrencyExchange(String baseCode,
                                                   String targetCode,
                                                   Double amountToConvert) {
        //Solicitud de API - retorna DTO
        CurrencyExchangeDTO currencyExchangeDTO = ExchangeRateAPI.PairConversionRequest(
                baseCode,
                targetCode,
                amountToConvert
        );
        //Instanciar dos entidades Currency (moneda base y objetivo)
        Currency baseCurrency = new Currency(currencyExchangeDTO.baseCode());
        Currency targetCurrency = new Currency(currencyExchangeDTO.targetCode());
        //Instanciar y retornar entidad CurrencyExchange
        return new CurrencyExchange(
                baseCurrency,
                targetCurrency,
                currencyExchangeDTO.conversionRate(),
                currencyExchangeDTO.amount(),
                currencyExchangeDTO.conversionResult()
        );
    }

    public void updateSupportedCurrencies() {
        // Solicitud a la API para obtener los códigos
        SupportedCurrenciesDTO supportedCurrenciesDTO = ExchangeRateAPI.supportedCurrenciesRequest();
        validCurrencyCodesSet.clear();
        Map<String, String> currencies = supportedCurrenciesDTO.supportedCurrencies();
        for (String code : currencies.keySet()) {
            validCurrencyCodesSet.add(code); //Almacena los códigos en el set
        }
    }
    //Validación de códigos ISO ingresados por el usuario
    public boolean isValidCurrencyCode(String code) {
        return validCurrencyCodesSet.contains(code);
    }
}

