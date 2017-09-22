package edu.galileo.android.poo_ejercicio1.Entidades;

/**
 * Created by postgres on 20/9/2017.
 */

public class Usuario {

    private int id;
    private String usuario;
    private int edad;

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
