package com.pm1.ejercicio23.config;

public class Transacciones {
//    DASE DE DATOS DE SQLITE
    public static final String NameDataBase = "pm1_photo_DB";
//    TABLA DE SQLITE
    public static final String tablaFotos = "photoperson";
//    CAMPOS DE LA TABLA Fotos
    public static final String id = "id";
    public static final String imagen = "imagen";
    public static final String desc = "descripcion";
//    TRANSACCIONES DDL de PERSONAS
    public static final String CreateTablePhoto = "CREATE TABLE photoperson (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
        "imagen BLOB, descripcion TEXT)";
    public static final String DropTablePhoto = "DROP TABLE IF EXISTS photoperson";
    public static final String test1 = "select * from photoperson";

}
