package com.example.tp1conversor.model;

import java.io.Serializable;

public class Moneda implements Serializable{

    private String nombre;
    private double valorRespectoUSD; // cuánto vale 1 unidad en USD

    public Moneda(String nombre, double valorRespectoUSD) {
        this.nombre = nombre;
        this.valorRespectoUSD = valorRespectoUSD;
    }

    public String getNombre() {
        return nombre;
    }

    public double getValorRespectoUSD() {
        return valorRespectoUSD;
    }

    public void setValorRespectoUSD(double valorRespectoUSD) {
        this.valorRespectoUSD = valorRespectoUSD;
    }
}
