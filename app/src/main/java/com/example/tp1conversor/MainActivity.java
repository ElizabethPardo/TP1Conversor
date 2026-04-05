package com.example.tp1conversor;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp1conversor.databinding.ActivityMainBinding;
import com.example.tp1conversor.ui.MainActivityViewModel;
import com.example.tp1conversor.ui.ViewMainActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        binding.etDolar.setEnabled(false);
        binding.etEuros.setEnabled(false);


        viewModel.getErrorMensaje().observe(this, s ->
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show()
        );


        viewModel.getVistaMutable().observe(this, new Observer<ViewMainActivity>() {
            @Override
            public void onChanged(ViewMainActivity vista) {

                if (vista.getMonedaDestino() != null) {
                    binding.etValorDeConversion.setText(
                            String.valueOf(vista.getMonedaDestino().getValorRespectoUSD())
                    );
                }

                if (binding.etDolar.isEnabled()) {
                    binding.etDolar.setText(vista.getValorOrigen());
                    binding.etEuros.setText(vista.getValorDestino());
                } else {
                    binding.etEuros.setText(vista.getValorOrigen());
                    binding.etDolar.setText(vista.getValorDestino());
                }
            }
        });


        binding.rbtnDolares.setOnClickListener(v -> {
            binding.etEuros.setEnabled(false);
            binding.etDolar.setEnabled(true);

            binding.etValorDeConversion.setEnabled(false);
            binding.btnCambiarValor.setEnabled(false);

            viewModel.getValorConversion("dolar");
            viewModel.limpiarCampos(true);
        });



        binding.rbtnEuros.setOnClickListener(v -> {
            binding.etDolar.setEnabled(false);
            binding.etEuros.setEnabled(true);

            binding.etValorDeConversion.setEnabled(true);
            binding.btnCambiarValor.setEnabled(true);

            viewModel.getValorConversion("euro");
            viewModel.limpiarCampos(false);
        });


        binding.btnConvertir.setOnClickListener(v -> {

            boolean estado = binding.etDolar.isEnabled();

            String valorDolar = binding.etDolar.getText().toString().isEmpty()
                    ? "0" : binding.etDolar.getText().toString();

            String valorEuro = binding.etEuros.getText().toString().isEmpty()
                    ? "0" : binding.etEuros.getText().toString();

            viewModel.Conversor(estado, valorDolar, valorEuro);
        });


        binding.btnCambiarValor.setOnClickListener(v -> {

            boolean estado = binding.etDolar.isEnabled();

            viewModel.setValorConversion(binding.etValorDeConversion.getText().toString());
        });
    }
}