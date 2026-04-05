package com.example.tp1conversor.model;

import java.io.Serializable;

public class Moneda implements Serializable {

    private String moneda;
    private double valor;

    public Moneda(String moneda, double valor) {
        this.moneda = moneda;
        this.valor = valor;
    }

    public double convertir(double monto) {
        return monto * valor;
    }

    public double convertirInverso(double monto) {
        return monto / valor;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
