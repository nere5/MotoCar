package com.nere5.motocar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        registrarVehiculos();
        registrarDocumentos();
        registrarGastos();
        salir();
    }

    public void registrarVehiculos() {
        Button regVehiculo = findViewById(R.id.btnRegVehiculos);
        regVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, RegVehiculos.class);
                startActivity(intent);
            }
        });

    }

    public void registrarDocumentos() {
        Button regDocumentos = findViewById(R.id.btnRegDocumentos);
        regDocumentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, RegDocumentos.class);
                startActivity(intent);
            }
        });

    }

    public void registrarGastos() {
        Button registroGastos = findViewById(R.id.btnRegGastos);
        registroGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, RegGastos.class);
                startActivity(intent);
            }
        });

    }

    public void visualizarEstadisticas() {

    }

    public void salir() {
        Button salir = findViewById(R.id.btnSalirMenu);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}