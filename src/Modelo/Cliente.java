/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * 
 */
public class Cliente {
        private String dni;
        private String nombre;
        private String apellidos;
        private String telefono;
        private String direccion;
        private String correo;
        private boolean activo;
        private String ruta_foto;
        private int num_cliente;
        private String observaciones;
        private ArrayList<Mensualidad> mensualidades;

    public Cliente() {
        mensualidades= new ArrayList<>();
    }

    public Cliente(String dni, String nombre, String apellidos, String telefono, 
            String direccion, String correo, boolean activo, String ruta_foto, 
            int num_cliente, String observaciones) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.activo = activo;
        this.ruta_foto = ruta_foto;
        this.num_cliente = num_cliente;
        this.observaciones = observaciones;
        mensualidades= new ArrayList<>();
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getRuta_foto() {
        return ruta_foto;
    }

    public void setRuta_foto(String ruta_foto) {
        this.ruta_foto = ruta_foto;
    }

    public int getNum_cliente() {
        return num_cliente;
    }

    public void setNum_cliente(int num_cliente) {
        this.num_cliente = num_cliente;
    }

    public ArrayList<Mensualidad> getMensualidades() {
        return mensualidades;
    }

    public void setMensualidades(ArrayList<Mensualidad> mensualidades) {
        this.mensualidades = mensualidades;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
     
    public void addMensualidad(String dni){
        Mensualidad m= new Mensualidad(dni);
        this.mensualidades.add(m);
    }

    @Override
    public String toString() {
        return "(" + num_cliente + ") " + nombre + " " + apellidos ;
    }
    
    
        

}
