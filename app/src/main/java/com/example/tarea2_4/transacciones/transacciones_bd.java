package com.example.tarea2_4.transacciones;

public class transacciones_bd {

    // nombre de la BD
    public static final String nombre_bd = "bd_firmas";

    // tablas de la BD
    public static final String tabla = "firmas";

    // campos de la tabla
    public static final String id = "id";
    public static final String firma = "firma";
    public static final String descripcion = "descripcion";

    // consultas de base de datos DDL
    // creando la tabla con sus campos
    public static final String CreateTableFirmas = "CREATE TABLE firmas "+"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "firma BLOB, " +
            "descripcion TEXT)";

    // consultas de base de datos DML
    public static final String SelectTableFirmas = "SELECT firma, descripcion from " + transacciones_bd.tabla;
}
