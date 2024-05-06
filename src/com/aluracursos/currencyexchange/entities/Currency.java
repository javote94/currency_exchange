package com.aluracursos.currencyexchange.entities;

public class Currency {

    private String code;
    private String name;

    //Constructores
    public Currency() {
    }

    public Currency(String code) {
        this.code = code;
    }

    //Getters y Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //toString
    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
