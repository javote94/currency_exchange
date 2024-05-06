package com.aluracursos.currencyexchange.dtos;

import com.google.gson.annotations.SerializedName;

public record CurrencyExchangeDTO(
        @SerializedName("base_code") String baseCode,
        @SerializedName("target_code") String targetCode,
        @SerializedName("conversion_rate") Double conversionRate,
        @SerializedName("conversion_result") Double conversionResult,
        Double amount) {
}
