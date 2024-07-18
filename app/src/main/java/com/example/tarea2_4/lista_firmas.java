package com.example.tarea2_4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea2_4.Modelo.signaturess;
import com.example.tarea2_4.transacciones.connection;
import com.example.tarea2_4.transacciones.transacciones_bd;

import java.util.ArrayList;
import java.util.List;

public class lista_firmas extends AppCompatActivity {

    /* Declaración de variables */
    private RecyclerView recyclerView;

    CardView_CustomAdapter myAdapter;

    private List<signaturess> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_firmas);
        /*Casteando el recyclerview*/
        recyclerView = findViewById(R.id.listaFirmas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getDatos();
        // Obtén tus datos como lo hacías antes
        List<signaturess> dataList = getAllFirmas();

        // Configura el adaptador
        myAdapter = new CardView_CustomAdapter(getApplicationContext(),dataList);
        recyclerView.setAdapter(myAdapter);
    }

    public List<signaturess> getAllFirmas() {
        List<signaturess> firmas = new ArrayList<>();
        connection con  = new connection(this, transacciones_bd.nombre_bd, null, 1);
        SQLiteDatabase db = con.getReadableDatabase();
        Cursor cursor = db.rawQuery(transacciones_bd.SelectTableFirmas,null);

        if (cursor.moveToFirst()) {
            do {
                int columnIndexFirma = cursor.getColumnIndex(transacciones_bd.firma);
                int columnIndexDescripcion = cursor.getColumnIndex(transacciones_bd.descripcion);

                signaturess modelo = new signaturess();
                modelo.setFirma(cursor.getBlob(columnIndexFirma));
                modelo.setDescripcion(cursor.getString(columnIndexDescripcion));
                // Agrega el modelo a la lista
                firmas.add(modelo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return firmas;
    }

    private void getDatos() {
        connection con  = new connection(this, transacciones_bd.nombre_bd, null, 1);
        SQLiteDatabase db = con.getReadableDatabase();
        signaturess firmas = null;
        Cursor cursor = db.rawQuery(transacciones_bd.SelectTableFirmas,null);
        while (cursor.moveToNext()){
            firmas = new signaturess();
            firmas.setDescripcion(cursor.getString(1));
            lista.add(firmas);
        }
        cursor.close();
    }
}