package com.nere5.motocar.Db;

public class ConstantesDb {
    public static final String DBNAME = "DbMotocar";
    public static final int DBVERSION = 1;
    public static final String TBL_NAME_USUARIOS = "Usuarios";
    public static final String USUARIOS_ID= "idUsuario";
    public static final String USUARIOS_NOMBRES = "nombres";
    public static final String USUARIOS_APELLIDOS = "apellidos";
    public static final String USUARIOS_CORREO = "correo";
    public static final String USUARIOS_CLAVE= "clave";

    public static final String CREAR_TBLUSUARIOS = "create table " + TBL_NAME_USUARIOS + "(" +
            ConstantesDb.USUARIOS_ID + " integer primary key autoincrement, " + ConstantesDb.USUARIOS_NOMBRES +
            " text not null, " + ConstantesDb.USUARIOS_APELLIDOS + " text not null, " +
            ConstantesDb.USUARIOS_CORREO + " text not null, " + ConstantesDb.USUARIOS_CLAVE +
            " text not null)";
}
