package com.example.tp1conversor.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp1conversor.model.Moneda;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel  extends AndroidViewModel {

    private List<Moneda> billetera;
    private ViewMainActivity viewMainActivity;
    private MutableLiveData<ViewMainActivity> vmutable;
    private MutableLiveData<String> errorMensaje;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        initMonedas();
        viewMainActivity = new ViewMainActivity();
    }

    public LiveData<ViewMainActivity> getVistaMutable (){
        if(vmutable == null){
            vmutable = new MutableLiveData<>();
        }
        return vmutable;
    }

    public LiveData<String> getErrorMensaje (){
        if(errorMensaje == null){
            errorMensaje = new MutableLiveData<>();
        }
        return errorMensaje;
    }

    public double convertir(Moneda origen, Moneda destino, double monto) {

        double enUSD = monto * origen.getValorRespectoUSD();

        return enUSD / destino.getValorRespectoUSD();
    }

    public void getValorConversion(String nombre){

        Moneda monedaEncontrada = billetera.stream()
                .filter(moneda -> moneda.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);

        if(monedaEncontrada == null){
            errorMensaje.setValue("La moneda no se encuentra en la billetera");
            return;
        }

        // dependiendo de cuál selecciona el usuario
        if(nombre.equalsIgnoreCase("dolar")){
            viewMainActivity.setMonedaOrigen(monedaEncontrada);


            Moneda euro = billetera.stream()
                    .filter(m -> m.getNombre().equalsIgnoreCase("Euro"))
                    .findFirst().orElse(null);

            viewMainActivity.setMonedaDestino(euro);

        } else {
            viewMainActivity.setMonedaOrigen(monedaEncontrada);

            Moneda dolar = billetera.stream()
                    .filter(m -> m.getNombre().equalsIgnoreCase("Dolar"))
                    .findFirst().orElse(null);

            viewMainActivity.setMonedaDestino(dolar);
        }

        vmutable.setValue(viewMainActivity);
    }

    public void setValorConversion(String valor){

        try {
            double nuevoValor = Double.parseDouble(valor.replace(",", "."));

            if(nuevoValor <= 0){
                errorMensaje.setValue("El valor debe ser mayor a 0");
                return;
            }

            Moneda euro = billetera.stream()
                    .filter(m -> m.getNombre().equalsIgnoreCase("Euro"))
                    .findFirst()
                    .orElse(null);

            if(euro == null){
                errorMensaje.setValue("No se encontró el Euro");
                return;
            }

            euro.setValorRespectoUSD(nuevoValor);

            vmutable.setValue(viewMainActivity);

        } catch (NumberFormatException e){
            errorMensaje.setValue("Ingrese un número válido");
        }
    }
    public void Conversor(boolean estado, String valorDolar, String valorEuro){

        Moneda dolar = billetera.stream()
                .filter(m -> m.getNombre().equalsIgnoreCase("Dolar"))
                .findFirst().orElse(null);

        Moneda euro = billetera.stream()
                .filter(m -> m.getNombre().equalsIgnoreCase("Euro"))
                .findFirst().orElse(null);

        if(dolar == null || euro == null){
            errorMensaje.setValue("Error en monedas");
            return;
        }

        double valor;

        try {
            if(estado){

                valorDolar = valorDolar.replace(",", ".");
                valor = Double.parseDouble(valorDolar);

                double resultado = convertir(dolar, euro, valor);

                viewMainActivity.setMonedaOrigen(dolar);
                viewMainActivity.setMonedaDestino(euro);
                viewMainActivity.setValorOrigen(valorDolar);
                viewMainActivity.setValorDestino(String.valueOf(resultado));

            }else{

                valorEuro = valorEuro.replace(",", ".");
                valor = Double.parseDouble(valorEuro);

                double resultado = convertir(euro, dolar, valor);

                viewMainActivity.setMonedaOrigen(euro);
                viewMainActivity.setMonedaDestino(dolar);
                viewMainActivity.setValorOrigen(valorEuro);
                viewMainActivity.setValorDestino(String.valueOf(resultado));
            }

            vmutable.setValue(viewMainActivity);

        } catch (NumberFormatException e){
            errorMensaje.setValue("Ingrese un número válido");
        }
    }
    private void initMonedas (){
        billetera = new ArrayList<>();

        Moneda dolar = new Moneda("Dolar", 1);
        Moneda euro = new Moneda("Euro", 0.92);

        billetera.add(dolar);
        billetera.add(euro);
    }

    public void limpiarCampos(boolean estado){

        if(estado){

            viewMainActivity.setValorOrigen("");
            viewMainActivity.setValorDestino("");
        } else {

            viewMainActivity.setValorOrigen("");
            viewMainActivity.setValorDestino("");
        }

        vmutable.setValue(viewMainActivity);
    }

}
