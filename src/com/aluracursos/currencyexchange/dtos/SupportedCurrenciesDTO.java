package com.aluracursos.currencyexchange.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record SupportedCurrenciesDTO(
        @SerializedName("supported_codes") Map<String, String> supportedCurrencies) {
}
