package com.example.tp1conversor.ui;

import com.example.tp1conversor.model.Moneda;

import java.io.Serializable;

public class ViewMainActivity  implements Serializable {

    private String valorOrigen;
    private String valorDestino;
    private Moneda monedaOrigen;
    private Moneda monedaDestino;

    public ViewMainActivity() {
        this.valorOrigen = "";
        this.valorDestino = "";
    }

    public String getValorOrigen() {
        return valorOrigen;
    }

    public void setValorOrigen(String valorOrigen) {
        this.valorOrigen = valorOrigen;
    }

    public String getValorDestino() {
        return valorDestino;
    }

    public void setValorDestino(String valorDestino) {
        this.valorDestino = valorDestino;
    }

    public Moneda getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(Moneda monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public Moneda getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(Moneda monedaDestino) {
        this.monedaDestino = monedaDestino;
    }
}
