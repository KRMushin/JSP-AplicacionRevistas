/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Modelos;

import java.io.InputStream;

/**
 *
 * @author kevin-mushin
 */
public class Anuncio {
    
    
    private String nombreAnunciante;
    private InputStream anuncio;

    public Anuncio() {
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public InputStream getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(InputStream anuncio) {
        this.anuncio = anuncio;
    }
    
    
    
}
