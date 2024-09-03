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
    
    private final Long id;
    private final Rol rol;
    private final String nombreUsuario;
    private final String password;
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
    
    /*      ATRITBUTOS NO EDITABLES */

    public Rol getRol() {
        return rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }
    
    public Usuario(Long id,Rol rol, String nombreUsuario, String password, String nombre) {
    // CONSTRUCTOR 
        this.id = id;
        this.rol = rol;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.nombre = nombre;
        this.preferencias = new ArrayList<>();
        
    }
    
    
}
