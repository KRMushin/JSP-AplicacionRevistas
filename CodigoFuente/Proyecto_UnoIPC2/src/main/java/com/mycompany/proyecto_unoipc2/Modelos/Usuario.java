/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Modelos;

import com.mycompany.proyecto_unoipc2.Utileria.Rol;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class Usuario {
    
    private Rol rol;
    private String nombreUsuario; /* llaver primaria*/
    private String password;
    private String nombre;
    private String descripcion;
    private byte[] foto;
    private List<PreferenciaUsuario> preferencias;
    
    /*  AREA SETTERS Y GETTERS*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public List<PreferenciaUsuario> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<PreferenciaUsuario> preferencias) {
        this.preferencias = preferencias;
    }
   

    public Rol getRol() {
        return rol;
    }
            
    /*      ATRITBUTOS NO EDITABLES */

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }
    /*  cosntructor vacio para construir nuevamente el user en la */
    public Usuario(){
    
    }
    public Usuario(String nombreUsuario, String password,Rol rol, String nombrePila) {
    // CONSTRUCTOR PARA METODOS DE REGISTRO
        this.rol = rol;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.nombre = nombrePila;
        this.preferencias = new ArrayList<>();
        this.foto = null;
        this.descripcion = null;
    }    
}
