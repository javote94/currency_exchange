package com.aluracursos.currencyexchange.ui;

import com.aluracursos.currencyexchange.entities.CurrencyExchange;
import com.aluracursos.currencyexchange.services.CurrencyExchangeService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private Scanner read;
    private CurrencyExchangeService service;

    public Menu(Scanner read, CurrencyExchangeService service) {
        this.read = read;
        this.service = service;
    }

    public void showMenu() {

        System.out.println(
                """
                
                ********************************************************************
                ********************** CONVERSOR DE MONEDAS ************************
                ********************************************************************
                
                Se notifica al usuario que para realizar operaciones de conversión
                debe proporcionar el código estandarizado de monedas reconocido
                internacionalmente bajo la norma ISO 4217
                """
        );

        int menuOption = 0;
        do {
            try {
                System.out.println(
                        """
                        
                        --------- MENÚ DE OPCIONES ---------
                        1. Registro de monedas admitidas
                        2. Solicitar conversión de divisas
                        3. Mostrar historial de solicitudes
                        4. Salir de la aplicación
                        ------------------------------------
                        """
                );
                System.out.print("Por favor, ingrese una opción del menú: ");
                menuOption = Integer.parseInt(read.nextLine().trim());

                switch (menuOption) {
                    case 1:
                        displaySupportedCurrencies();
                        break;
                    case 2:
                        CurrencyExchange currencyExchange = performCurrencyExchange();
                        showCurrencyExchange(currencyExchange);
                        break;
                    case 3:
                        displayConversionHistory();
                        break;
                    case 4:
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, ingrese una opción disponible en el menú");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese una opción disponible en el menú");
                continue;  //Continuar con la próxima iteración del bucle
            }
        } while (continueInMenu(menuOption));

        System.out.println(
                """
                
                Le agradecemos por su visita. Vuelva pronto!
                """
        );
    }

    //Verificar condición del bucle del menú
    private boolean continueInMenu(int menuOption) {
        if (menuOption == 1 || menuOption == 2 || menuOption == 3) {
            while (true) {  //Añadir bucle para reintentar hasta obtener una respuesta válida
                try {
                    System.out.println(
                            """
                            ------------------------------------
                            Desea volver al menú de la aplicación?
                            1. Si
                            2. No
                            """
                    );
                    System.out.print("Por favor, ingrese una respuesta: ");
                    int backToMenu = Integer.parseInt(read.nextLine().trim());

                    if (backToMenu == 1 || backToMenu == 2) {
                        return backToMenu == 1;  //Retorna TRUE si la respuesta es 1, FALSE si es 2
                    } else {
                        System.out.println("Entrada inválida. Por favor, ingrese 1 para Sí o 2 para No.");
                    }

                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, ingrese 1 para Sí o 2 para No.");
                }
            }
        }
        if (menuOption > 4 || menuOption < 1) {
            return true;
        }
        return false;
    }

    //Opción 1: solicitud de listado de monedas admitidas por la aplicación
    private void displaySupportedCurrencies() {
        Map<String, String> supportedCurrencies = service.requestSupportedCurrencies();

        System.out.println("MONEDA | CODIGO ISO");
        for (Map.Entry<String, String> currency : supportedCurrencies.entrySet()) {
            System.out.println(currency.getValue() + " | " + currency.getKey());
        }
    }

    //Opción 2: solicitud de conversión entre divisas
    private CurrencyExchange performCurrencyExchange() {
        String baseCode, targetCode;
        //Moneda base
        do {
            System.out.print("Ingresar moneda de base [código ISO]: ");
            baseCode = read.nextLine().trim().toUpperCase();
            if (!service.isValidCurrencyCode(baseCode)) {
                System.out.println("Código de moneda base inválido. Por favor, ingrese un código ISO válido.");
            }
        } while (!service.isValidCurrencyCode(baseCode));
        //Moneda objetivo
        do {
            System.out.print("Ingresar moneda de destino [código ISO]: ");
            targetCode = read.nextLine().trim().toUpperCase();
            if (!service.isValidCurrencyCode(targetCode)) {
                System.out.println("Código de moneda destino inválido. Por favor, ingrese un código ISO válido.");
            }
        } while (!service.isValidCurrencyCode(targetCode));
        //Importe de la operación
        Double amountToConvert = null;
        while (amountToConvert == null) {
            try {
                System.out.print("Ingresar monto en [" + baseCode + "] que desea convertir a [" + targetCode + "]: ");
                amountToConvert = Double.parseDouble(read.nextLine().trim().replace(",","."));
            } catch (NumberFormatException e) {
                System.out.println("Error: Formato de número inválido. Asegúrese de ingresar un número válido.");
            }
        }
        return service.requestCurrencyExchange(
                baseCode,
                targetCode,
                amountToConvert
        );
    }

    //Mostrar al usuario los resultado de la conversión entre divisas
    private void showCurrencyExchange(CurrencyExchange currencyExchange) {
        System.out.println(
                String.format(
                        """
                        
                        ---------------- OPERACIÓN -----------------
                        Conversión:  [%s] --> [%s]
                        Cotización:  %s 1 = %s %.2f
                        Importe de la operación:  %s %.2f
                        Resultado de la conversión:  %s %.2f
                        --------------------------------------------
                        """,
                        currencyExchange.getBaseCurrency().getCode(),
                        currencyExchange.getTargetCurrency().getCode(),
                        currencyExchange.getBaseCurrency().getCode(),
                        currencyExchange.getTargetCurrency().getCode(),
                        currencyExchange.getConversionRate(),
                        currencyExchange.getBaseCurrency().getCode(),
                        currencyExchange.getAmountToConvert(),
                        currencyExchange.getTargetCurrency().getCode(),
                        currencyExchange.getConversionResult()
                )
        );
    }

    //Opción 3: solicitud del historial de conversiones
    private void displayConversionHistory() {
        List<CurrencyExchange> currencyExchangeList = service.requestCurrenciesExchangesHistory();
        for (CurrencyExchange ce : currencyExchangeList) {
            System.out.println(
                    String.format(
                            """
                            Moneda base: %s | Moneda destino: %s | Cotización: %s 1 = %s %.2f | Importe de la operación: %s %.2f | Resultado de la conversión: %s %.2f
                            """,
                            ce.getBaseCurrency().getCode(),
                            ce.getTargetCurrency().getCode(),
                            ce.getBaseCurrency().getCode(),
                            ce.getTargetCurrency().getCode(),
                            ce.getConversionRate(),
                            ce.getBaseCurrency().getCode(),
                            ce.getAmountToConvert(),
                            ce.getTargetCurrency().getCode(),
                            ce.getConversionResult()
                    )
            );
        }
    }

}
