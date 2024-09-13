/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Modelos;

/**
 *
 * @author kevin-mushin
 */
public class Etiqueta {
    
    private Long idEtiqueta;
    private String etiqueta;

    public Etiqueta() {
    }

    public Etiqueta(Long idEtiqueta, String etiqueta) {
        this.idEtiqueta = idEtiqueta;
        this.etiqueta = etiqueta;
    }
    
    public boolean esEtiquetaValida() {
        if (this.etiqueta != null && !this.etiqueta.isEmpty()) {
            return this.etiqueta.matches("[a-zA-Z]+");
        }
        return false; 
    }


    public Long getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(Long idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    
    
    
    
}
