/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Modelos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 *
 * @author kevin-mushin
 */
public class FotoUsuario {
    
    private Long idFoto;
    private String nombreUsuario;
    private InputStream foto;

    public FotoUsuario() {
    }

    public FotoUsuario(Long idFoto, String nombreUsuario, InputStream foto) {
        this.idFoto = idFoto;
        this.nombreUsuario = nombreUsuario;
        this.foto = foto;
    }

    
    public Long getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Long idFoto) {
        this.idFoto = idFoto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    // Método para establecer una nueva imagen desde un archivo subido
    public void setFotoFromBytes(byte[] imageBytes) {
        this.foto = new java.io.ByteArrayInputStream(imageBytes);
    }

  
}
