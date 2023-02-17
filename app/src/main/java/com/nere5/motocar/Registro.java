package com.nere5.motocar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
        ConexionDb conexion = new ConexionDb(this, ConstantesDb.DBNAME, null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {campoCorreo.getText().toString()};
        String[] campos = {ConstantesDb.USUARIOS_CORREO};
        if (nombres.equals("") | apellidos.equals("") | correo.equals("") | clave.equals("")
                | confClave.equals("")) {
            Toast.makeText(this, "Llene los campos vacios", Toast.LENGTH_LONG).show();
        } else {
            Cursor cursor = db.query(ConstantesDb.TBL_NAME_USUARIOS, campos,
                    ConstantesDb.USUARIOS_CORREO + " =?", parametros, null, null,
                    null);
            cursor.moveToFirst();
            try {
                if (cursor.getString(0).equals(correo)) {
                    campoNombres.setText("");
                    campoApellidos.setText("");
                    campoCorreo.setText("");
                    campoClave.setText("");
                    campoConfClave.setText("");
                    Toast.makeText(this, "El usuario ya existe\nIngrese los datos nuevamente",
                            Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                cursor.close();
                if (clave.equals(confClave)) {
                    Toast.makeText(this, "Registro de usuario exitoso", Toast.LENGTH_LONG).show();
                    registrar();
                } else {
                    campoClave.setText("");
                    campoConfClave.setText("");
                    campoClave.requestFocus();
                    Toast.makeText(this, "Las claves no coinciden\nIngréselas nuevamente",
                            Toast.LENGTH_LONG).show();
                }
            }
            cursor.close();
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