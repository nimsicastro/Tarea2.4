package com.example.tarea2_4.transacciones;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/*  Esta clase es para la creación y la actualización de la base de datos.  */
public class connection extends SQLiteOpenHelper
{
    public connection(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // crea los objetos de base de datos
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {  // se llama cuando la BD se crea por primera vez
        sqLiteDatabase.execSQL(transacciones_bd.CreateTableFirmas); // creando la tabla como tal
    }

    // actualiza o destruye los objetos
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
