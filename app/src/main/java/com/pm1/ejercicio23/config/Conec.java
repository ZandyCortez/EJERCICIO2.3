package com.pm1.ejercicio23.config;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conec extends SQLiteOpenHelper{
    //contexto-nombre de DB-CURSOR-Version
    public Conec(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //crear la tabla Photograh
        sqLiteDatabase.execSQL(Transacciones.CreateTablePhoto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldver, int newver) {
        //Eliminar las tablas al eliminar la info de DB
        sqLiteDatabase.execSQL(Transacciones.DropTablePhoto);
        onCreate(sqLiteDatabase);
    }
}
