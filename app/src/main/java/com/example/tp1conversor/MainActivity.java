package com.example.tp1conversor;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp1conversor.databinding.ActivityMainBinding;
import com.example.tp1conversor.ui.MainActivityViewModel;
import com.example.tp1conversor.ui.ViewMainActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private String monedaSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        binding.etDolar.setEnabled(false);
        binding.etEuros.setEnabled(false);

        viewModel.getErrorMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getVistaMutable().observe(this, new Observer<ViewMainActivity>() {
            @Override
            public void onChanged(ViewMainActivity vistaMainActivity) {
                binding.etValorDeConversion.setText(String.valueOf(vistaMainActivity.getMoneda().getValor()));
                binding.etDolar.setText(vistaMainActivity.getValorDolar());
                binding.etEuros.setText(vistaMainActivity.getValorEuro());
            }
        });


        binding.rbtnDolares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etEuros.setEnabled(false);
                binding.etDolar.setEnabled(true);
                binding.etDolar.setText("");
                viewModel.getValorConversion("dolar");
            }
        });

        binding.rbtnEuros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etDolar.setEnabled(false);
                binding.etEuros.setEnabled(true);
                binding.etEuros.setText("");
                binding.etValorDeConversion.setEnabled(true);
                viewModel.getValorConversion("euro");
            }
        });


        binding.btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean estado = binding.etDolar.isEnabled();
                String valorDolar = binding.etDolar.getText().toString().isEmpty() ? "0" : binding.etDolar.getText().toString();
                String valorEuro = binding.etEuros.getText().toString().isEmpty() ? "0" : binding.etEuros.getText().toString();

                viewModel.Conversor(estado,valorDolar,valorEuro);
            }
        });

        binding.btnCambiarValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setValorConversion(binding.etValorDeConversion.getText().toString());
            }
        });
    }
}