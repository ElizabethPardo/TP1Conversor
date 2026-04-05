package com.example.tp1conversor.ui;

import com.example.tp1conversor.model.Moneda;

public class ViewMainActivity {

    private String valorDolar;
    private String valorEuro;
    private Moneda moneda;

    public ViewMainActivity() {
        this.valorDolar = "";
        this.valorEuro = "";
    }

    public ViewMainActivity(String valorDolar, String valorEuro, Moneda moneda) {
        this.valorDolar = valorDolar;
        this.valorEuro = valorEuro;
        this.moneda = moneda;
    }

    public String getValorDolar() {
        return valorDolar;
    }

    public void setValorDolar(String valorDolar) {
        this.valorDolar = valorDolar;
    }

    public String getValorEuro() {
        return valorEuro;
    }

    public void setValorEuro(String valorEuro) {
        this.valorEuro = valorEuro;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
}
