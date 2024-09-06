/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Modelos;

import com.mycompany.proyecto_unoipc2.backend.Utileria.Rol;
import java.io.InputStream;
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
    private InputStream foto;
    private List<PreferenciaUsuario> preferencias;
    
    /*  cosntructor vacio para construir nuevamente el user en la */
    public Usuario(){
        this.preferencias = new ArrayList<>();
    
    }
    // usuario con campos obligatorios
    public boolean esValido(){
        
    return rol != null && nombreUsuario != null && password != null && nombre != null;
    }
    
    /*              AREA SETTERS Y  GETTERS                                 */
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public List<PreferenciaUsuario> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<PreferenciaUsuario> preferencias) {
        this.preferencias = preferencias;
    }
   
}
