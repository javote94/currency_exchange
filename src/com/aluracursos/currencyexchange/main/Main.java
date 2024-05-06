package com.aluracursos.currencyexchange.main;

import com.aluracursos.currencyexchange.services.CurrencyExchangeService;
import com.aluracursos.currencyexchange.ui.Menu;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            Scanner read = new Scanner(System.in).useDelimiter("\n");
            CurrencyExchangeService service = new CurrencyExchangeService();

            Menu menu = new Menu(read, service);
            menu.showMenu();

        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado al iniciar el programa: " + e.getMessage());
            e.printStackTrace();
        }

    }
}

