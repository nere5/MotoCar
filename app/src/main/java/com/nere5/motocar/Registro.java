package com.nere5.motocar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nere5.motocar.Db.ConexionDb;
import com.nere5.motocar.Db.ConstantesDb;

public class Registro extends AppCompatActivity {

    private EditText campoNombres, campoApellidos, campoCorreo, campoClave, campoConfClave;
    private String nombres, apellidos, correo, clave, confClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        campoNombres = findViewById(R.id.edtNombres);
        campoApellidos = findViewById(R.id.edtApellidos);
        campoCorreo = findViewById(R.id.edtCorreo);
        campoClave = findViewById(R.id.edtClave);
        campoConfClave = findViewById(R.id.edtConfClave);

    }

    public void onClick(View view) {

        switch (view.getId()) {
            case (R.id.btnAceptar):
                capturarDatos();
                validar();
                break;

            case (R.id.btnSalirRegistro):
                finish();
                break;
        }
    }

    private void capturarDatos() {
        nombres = campoNombres.getText().toString();
        apellidos = campoApellidos.getText().toString();
        correo = campoCorreo.getText().toString();
        clave = campoClave.getText().toString();
        confClave = campoConfClave.getText().toString();
    }

    private void validar() {

        if (clave.equals(confClave)) {
            Toast.makeText(this, "Registro de usuario exitoso", Toast.LENGTH_LONG).show();
            registrar();
        } else {
            campoClave.setText("");
            campoConfClave.setText("");
            campoClave.requestFocus();
            Toast.makeText(this, "Las claves no coinciden. \n Ingréselas nuevamente",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void registrar() {
        ConexionDb conexion = new ConexionDb(this, ConstantesDb.DBNAME, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String insert = "insert into " + ConstantesDb.TBL_NAME_USUARIOS + "(" + ConstantesDb.USUARIOS_NOMBRES +
                ", " + ConstantesDb.USUARIOS_APELLIDOS + ", " + ConstantesDb.USUARIOS_CORREO + ", " +
                ConstantesDb.USUARIOS_CLAVE + ")" + " values('" + nombres + "', '" + apellidos + "', '" +
                correo + "', '" + clave + "')";
        db.execSQL(insert);
        db.close();
        Intent intent = new Intent(Registro.this, Menu.class);
        startActivity(intent);
    }

}