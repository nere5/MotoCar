package com.nere5.motocar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nere5.motocar.Db.ConexionDb;
import com.nere5.motocar.Db.ConstantesDb;

public class InicioSesion extends AppCompatActivity {

    EditText campoCorreo, campoClave;
    String correo, clave;

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
                campoCorreo = findViewById(R.id.edtCorreoInicioSesion);
                correo = campoCorreo.getText().toString();
                campoClave = findViewById(R.id.edtClaveInicioSesion);
                clave = campoClave.getText().toString();

                ConexionDb conexionDb = new ConexionDb(InicioSesion.this, ConstantesDb.DBNAME,
                        null, ConstantesDb.DBVERSION);
                SQLiteDatabase db = conexionDb.getReadableDatabase();
                String[] parametros = {correo};
                String[] campos = {ConstantesDb.USUARIOS_CORREO, ConstantesDb.USUARIOS_CLAVE};
                Cursor cursor = db.query(ConstantesDb.TBL_NAME_USUARIOS, campos,
                        ConstantesDb.USUARIOS_CORREO + " =?", parametros,
                        null, null, null);
                cursor.moveToFirst();
                if (correo.isEmpty() | clave.isEmpty()) {
                    Toast.makeText(InicioSesion.this, "LLene los campos vacios",
                            Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        if (cursor.getString(0).equals(correo)) {
                            if (cursor.getString(1).equals(clave)) {
                                Intent intent = new Intent(InicioSesion.this, Menu.class);
                                startActivity(intent);
                            } else {
                                campoClave.setText("");
                                campoClave.requestFocus();
                                Toast.makeText(InicioSesion.this, "Clave no valida\n" +
                                        "ingrésela nuevamente", Toast.LENGTH_LONG).show();
                            }

                        }

                    } catch (Exception e) {
                        Toast.makeText(InicioSesion.this, "Usuario no registrado",
                                Toast.LENGTH_SHORT).show();
                    }

                }
                cursor.close();
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