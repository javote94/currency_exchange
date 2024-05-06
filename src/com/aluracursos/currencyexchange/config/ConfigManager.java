package com.aluracursos.currencyexchange.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties prop = new Properties();

    static {
        try (InputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("No se puede cargar la configuración: " + e.getMessage());
        }
    }

    public static String getApiKey() {
        return prop.getProperty("api_key");
    }
}
