package com.aluracursos.currencyexchange.services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFile {

    static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();

    public static void writeJsonFile(String conversion) {

        try {
            FileWriter fileJson = new FileWriter("conversion.json");
            fileJson.write(gson.toJson(conversion));
            fileJson.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
