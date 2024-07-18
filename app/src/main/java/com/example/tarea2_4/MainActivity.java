package com.example.tarea2_4;

import static com.example.tarea2_4.Canva_FirmaDigital.isTouched;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tarea2_4.transacciones.connection;
import com.example.tarea2_4.transacciones.transacciones_bd;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private Canva_FirmaDigital signaturePad;
    Button guardarFirmas, verFirmas;
    TextView descripcion;

    connection conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Casteando los valores y colocando los eventos para botones */
        guardarFirmas = (Button) findViewById(R.id.btnGuardar);
        verFirmas = (Button) findViewById(R.id.btnVerFirmas);
        descripcion = (TextView) findViewById(R.id.txtDescripcionFirma);
        signaturePad = findViewById(R.id.firma_digital);


        guardarFirmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validar() == true){
                    saveFirmas();
                }else{
                    mensajesVacios();
                }
            }
        });

        verFirmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lista_fotos = new Intent(getApplicationContext(), lista_firmas.class);
                startActivity(lista_fotos);
            }
        });
    }
    public boolean validar(){
        boolean retorna = true;

        if(descripcion.getText().toString().isEmpty()){
            retorna = false;
        }
        //validar si la firma esta vacia

        if(isTouched == false){
            retorna = false;
        }
        return retorna;
    }
    private void mensajesVacios() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(descripcion.getText().toString().isEmpty()){
            builder.setTitle("Advertencia");
            builder.setMessage("Escriba su descripción por favor");
        }
        if(isTouched == false){
            builder.setTitle("Advertencia");
            builder.setMessage("Dibuje su firma digital");
        }
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void saveFirmas()
    {
        try {
            conexion = new connection(this, transacciones_bd.nombre_bd, null, 1);
            SQLiteDatabase db =  conexion.getWritableDatabase();
            byte[] firmaData = obtenerFirma();

            ContentValues valores = new ContentValues();
            valores.put(transacciones_bd.descripcion, descripcion.getText().toString());
            valores.put(transacciones_bd.firma, firmaData);

            Long Result = db.insert(transacciones_bd.tabla, transacciones_bd.id, valores);
            message();
            //Utilizando AlertDialog.Builder
            db.close();
        }
        catch (Exception exception)
        {
            //Utilizando AlertDialog.Builder
            Log.d("El error",""+exception);
            error();
        }

    }

    private void message() {
        isTouched = false;
        //Creando el AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Configurar el AlertDialog.Builder
        builder.setTitle("Registro exitoso");
        builder.setMessage("La firma ha sido creada correctamente");

        //Botón para cerrar el cuadro de dialogo
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Cerrar el cuadro de diálogo
                dialog.dismiss();
            }
        });

        // Mostrar el cuadro de diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
        signaturePad.clearCanvas();
        descripcion.setText("");
    }

    private void error() {
        //Creando el AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Configurar el AlertDialog.Builder
        builder.setTitle("Error al registrar");
        builder.setMessage("La firma no se ha podido guardar.");

        //Botón para cerrar el cuadro de dialogo
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Cerrar el cuadro de diálogo
                dialog.dismiss();
            }
        });
        // Mostrar el cuadro de diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public byte[] obtenerFirma() {
        Bitmap signatureBitmap = signaturePad.getSignatureBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}