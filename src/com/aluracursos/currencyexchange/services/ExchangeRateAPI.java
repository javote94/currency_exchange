package com.aluracursos.currencyexchange.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.aluracursos.currencyexchange.config.ConfigManager;
import com.aluracursos.currencyexchange.dtos.CurrencyExchangeDTO;
import com.aluracursos.currencyexchange.dtos.SupportedCurrenciesDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateAPI {

    private static String apiKey = ConfigManager.getApiKey();
    private static final HttpClient client = HttpClient.newHttpClient();
    private static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    //Opción 1: listado de monedas admitidas por la aplicación (retorna DTO)
    public static SupportedCurrenciesDTO supportedCurrenciesRequest() {

        URI endpoint = URI.create("https://v6.exchangerate-api.com/v6/" +
                apiKey + "/codes");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpoint)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //Deserializar JSON a objeto DTO
            SupportedCurrenciesDTO dto = gson.fromJson(response.body(), SupportedCurrenciesDTO.class);
            return dto;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Opción 2: solicitud de conversión entre divisas (retorna DTO)
    public static CurrencyExchangeDTO PairConversionRequest(String baseCode,
                                                            String targetCode,
                                                            Double amountToConvert) {
        URI endpoint = URI.create("https://v6.exchangerate-api.com/v6/" +
                apiKey + "/pair/" + baseCode + "/" + targetCode + "/" + amountToConvert);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpoint)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //Deserializar JSON a objeto DTO
            CurrencyExchangeDTO dto = gson.fromJson(response.body(), CurrencyExchangeDTO.class);

            return new CurrencyExchangeDTO(
                    dto.baseCode(),
                    dto.targetCode(),
                    dto.conversionRate(),
                    dto.conversionResult(),
                    amountToConvert
            );
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
