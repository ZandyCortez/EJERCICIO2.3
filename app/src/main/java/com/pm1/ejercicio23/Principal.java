package com.pm1.ejercicio23;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pm1.ejercicio23.config.Conec;
import com.pm1.ejercicio23.config.Transacciones;
import com.pm1.ejercicio23.fotografias.Adaptador;
import com.pm1.ejercicio23.fotografias.Photograh;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Principal extends AppCompatActivity {

    static final int PETICION_ACCESO_CAM=201;
    static final int REQUEST_IMAGE_CAPTURE=1;
    CircleImageView circleView;
    Button btnsalvar;
    Button btnverregistros;
    Button btnreset;
    TextView descr;
    Bitmap imagen;
    byte[] bArrayfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descr=(TextView) findViewById(R.id.txtdescrip);

        circleView=(CircleImageView) findViewById(R.id.profile_image);
        circleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisos();
            }
        });

        btnverregistros=(Button) findViewById(R.id.btnVer);
        btnverregistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VerLista.class);
                startActivity(intent);
            }
        });

        btnreset=(Button) findViewById(R.id.btnresetimg);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarPantalla();
                bArrayfoto=null;
            }
        });

        btnsalvar=(Button) findViewById(R.id.btnsalva);
        btnsalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bArrayfoto != null && !descr.getText().toString().isEmpty()){
                    guardafoto();
                }else{
                    if(bArrayfoto == null)
                    {Toast.makeText(getApplicationContext(),"Presiona la Imagen para capturar la foto", Toast.LENGTH_LONG).show(); circleView.requestFocus();}
                    else if(descr.getText().toString().isEmpty())
                    {Toast.makeText(getApplicationContext(),"Ingrese una Descripcion", Toast.LENGTH_LONG).show(); descr.requestFocus();}
                }
            }
        });


    }

    private void guardafoto() {
        Conec conexion = new Conec(this, Transacciones.NameDataBase,null,1);//nueva conexion a a DB
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();                            //establecer los valores
        valores.put(Transacciones.desc,descr.getText().toString());             //descripcion
        valores.put(Transacciones.imagen, bArrayfoto);                          //enviar array de de la foto

        Long resultado = db.insert(Transacciones.tablaFotos,Transacciones.id,valores);
        Toast.makeText(getApplicationContext(),"Registro Ingresado! COD:"+resultado.toString(),Toast.LENGTH_LONG).show();

        db.close();

        limpiarPantalla();
        bArrayfoto=null;
    }

    private void limpiarPantalla() {
        circleView.setImageResource(R.drawable.person);
        descr.setText("");
    }

    private void permisos() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},PETICION_ACCESO_CAM);
        }
        else{
            TomarFoto();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PETICION_ACCESO_CAM){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                TomarFoto();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Se necesesita el permiso de la CAMARA", Toast.LENGTH_LONG).show();
        }
    }
    private void TomarFoto() {
        Intent tomarfoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(tomarfoto.resolveActivity(getPackageManager())!=null){
            startActivityForResult(tomarfoto,REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();       //sacar la imagen de extras
            imagen = (Bitmap) extras.get("data");   //enviarla a un bitmap
            circleView.setImageBitmap(imagen);      //establecerla en el circleview
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();    //convertir el bitmap a bit array
                imagen.compress(Bitmap.CompressFormat.PNG, 100, bos);
                bArrayfoto = bos.toByteArray();
            }catch (Exception ex){
                Toast.makeText(getApplicationContext(),"Error al convertir la foto", Toast.LENGTH_LONG).show();
                bArrayfoto=null;
                limpiarPantalla();
            }
        }
    }
}