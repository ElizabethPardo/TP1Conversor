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

    public void getValorConversion(String nombre){
        Moneda monedaEncontrada = billetera.stream()
                .filter(moneda -> moneda.getMoneda().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);

        if(monedaEncontrada == null){
            errorMensaje.setValue("La moneda no se encuentra en la billetera del sistema");
            return;
        }
        viewMainActivity.setMoneda(monedaEncontrada);
        vmutable.setValue(viewMainActivity);
    }

    public void setValorConversion(String valor){
        Moneda m = viewMainActivity.getMoneda();
        if(m == null){
            errorMensaje.setValue("Debe seleccionar una moneda antes de cambiar el valor");
            return;
        }
        m.setValor(Double.parseDouble(valor));
        vmutable.setValue(viewMainActivity);
    }
    public void Conversor(boolean estado, String valorDolar, String valorEuro){

        double valor;
        Moneda m = viewMainActivity.getMoneda();

        if(m == null){
            errorMensaje.setValue("Debe seleccionar una moneda");
            return;
        }

        if(estado){
            // DOLAR a EURO
            valor = Double.parseDouble(valorDolar);
            valor = m.convertir(valor);
            valorEuro = String.valueOf(valor);
        }else{
            // EURO a DOLAR
            valor = Double.parseDouble(valorEuro);
            valor = m.convertirInverso(valor);
            valorDolar = String.valueOf(valor);
        }

        viewMainActivity.setValorDolar(valorDolar);
        viewMainActivity.setValorEuro(valorEuro);
        vmutable.setValue(viewMainActivity);
    }



    private void initMonedas (){
        billetera = new ArrayList<>();
        Moneda dolar = new Moneda("Dolar", 1);
        Moneda euro = new Moneda("Euro", 0.92);

        if(billetera == null){
            errorMensaje.setValue("Error: billetera no inicializada");
            return;
        }
        billetera.add(dolar);
        billetera.add(euro);
    }

}
