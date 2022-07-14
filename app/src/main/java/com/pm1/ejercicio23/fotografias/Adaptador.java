package com.pm1.ejercicio23.fotografias;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pm1.ejercicio23.R;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>
{
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView descripcion;
        ImageView imagen;
        public ViewHolder (View itemView){
            super(itemView);
            descripcion=(TextView)itemView.findViewById(R.id.txt2);//obtenemos los obj de el activity ver lista
            imagen=(ImageView) itemView.findViewById(R.id.profile_image);
        }
    }

    public List<Photograh> lista;   //nueva lista de fotografias
    public Adaptador(List<Photograh>lista){
        this.lista=lista;
    }//iniciarmos un adaptador con la lista

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//le decimos al viewholder que use como card a el layout de item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ViewHolder viewholder=new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//le mandamos la lista a los obj ya creados
        holder.descripcion.setText(lista.get(position).getDescripcion());
        holder.imagen.setImageBitmap(BitmapFactory.decodeByteArray(lista.get(position).getImagen(),0,lista.get(position).getImagen().length));//conversion de byte[] a bitmap
    }
    @Override
    public int getItemCount() {
        return lista.size();
    }
}


