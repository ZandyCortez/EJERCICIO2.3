package com.pm1.ejercicio23.fotografias;

import android.graphics.Bitmap;

public class Photograh {
    private Integer id;
    private byte[] imagen;
//    private int imagen2;
    private String descripcion;


    public Photograh(Integer id, byte[] imagen, String descripcion) {
        this.id = id;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }
    public Photograh(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImagen() {
        return imagen;
    }

//    public int getImagen2() { return imagen2; }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
