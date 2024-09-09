/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Utileria;

/**
 *
 * @author kevin-mushin
 */
public class OpcionesUsuario {
    
    private String opcionNombre;
    private String opcionAccion;

    public OpcionesUsuario(String opcionNombre, String opcionAccion) {
        this.opcionNombre = opcionNombre;
        this.opcionAccion = opcionAccion;
    }

    
    public String getOpcionNombre() {
        return opcionNombre;
    }

    public void setOpcionNombre(String opcionNombre) {
        this.opcionNombre = opcionNombre;
    }

    public String getOpcionAccion() {
        return opcionAccion;
    }

    public void setOpcionAccion(String opcionAccion) {
        this.opcionAccion = opcionAccion;
    }
    
    
    
}
