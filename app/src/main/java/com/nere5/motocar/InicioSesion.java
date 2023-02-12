package com.nere5.motocar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        iniciarSesion();
        salir();
    }

    public void iniciarSesion() {
        Button inicioSesion = findViewById(R.id.btnEnviarInicioSesion);
        inicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioSesion.this, Menu.class);
                startActivity(intent);
            }
        });

    }

    public void salir() {
        Button salir = findViewById(R.id.btnSalirInicioSesion);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}