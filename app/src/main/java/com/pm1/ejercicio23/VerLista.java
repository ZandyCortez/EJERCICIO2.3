package com.pm1.ejercicio23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pm1.ejercicio23.config.Conec;
import com.pm1.ejercicio23.config.Transacciones;
import com.pm1.ejercicio23.fotografias.Adaptador;
import com.pm1.ejercicio23.fotografias.Photograh;

import java.util.ArrayList;
import java.util.List;

public class VerLista extends AppCompatActivity {
    private RecyclerView rview;
    private Adaptador rviewadapter;
    Conec conexion;
    ListView listfoto;
    ArrayList<Photograh> lista;
    ArrayList<String> ArregloFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lista);

        rview=(RecyclerView)findViewById(R.id.viewR);//iniciamos el recycle view
        rview.setLayoutManager(new LinearLayoutManager(this));//le damos un tipo de layout a el recycle

        rviewadapter=new Adaptador(ObtenerFoto());//creamos o inizializamos el adaptador con los datos de obtener foto
        rview.setAdapter(rviewadapter);//le ponemos el adaptador al recycle view

//        conexion=new Conec(this, Transacciones.NameDataBase,null,1);
//        listfoto=(ListView)findViewById(R.id.viewR);
//        ObtenerFoto();
//        ArrayAdapter adp = new ArrayAdapter( this, android.R.layout.simple_list_item_1, ArregloFoto);
//        listfoto.setAdapter(adp);
    }

//    public List<Photograh> Obtenerlista(){
//        byte[] data = new byte[14];
//        List<Photograh>datos=new ArrayList<>();
//        datos.add(new Photograh(44,data,"hola"));
//        return datos;
//    }

    private List<Photograh> ObtenerFoto()
    {
        Conec conexion = new Conec(this, Transacciones.NameDataBase,null,1);
        SQLiteDatabase db= conexion.getReadableDatabase();
        Photograh Lphoto=null;
        lista= new ArrayList<Photograh>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablaFotos, null);

        while(cursor.moveToNext())
        {
            Lphoto = new Photograh();
            Lphoto.setId(cursor.getInt(0));
            Lphoto.setDescripcion(cursor.getString(2));
            Lphoto.setImagen(cursor.getBlob(1));
            lista.add(Lphoto);
        }
        cursor.close();
//        filllistPhoto();
        return lista;
    }

//    private void filllistPhoto()
//    {
//        ArregloFoto=new ArrayList<String>();
//        for (int i=0; i< lista.size(); i++)
//        {
//            ArregloFoto.add(lista.get(i).getId() + " | "
//                    +lista.get(i).getDescripcion()+" | "
//                    +lista.get(i).getImagen());
//        }
//    }
}