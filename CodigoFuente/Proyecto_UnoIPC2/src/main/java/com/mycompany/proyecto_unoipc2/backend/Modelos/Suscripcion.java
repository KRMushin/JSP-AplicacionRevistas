/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Modelos;

import java.time.LocalDate;

/**
 *
 * @author kevin-mushin
 */
public class Suscripcion {
    
    private Long idRevista;
    private Long idSuscripcion;
    private String suscriptorUsuario;
    private LocalDate fechaSuscripcion;
    
    private String tituloSuscripcion;

    public Suscripcion() {
    }

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public Long getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Long idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public LocalDate getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(LocalDate fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public String getSuscriptorUsuario() {
        return suscriptorUsuario;
    }

    public void setSuscriptorUsuario(String suscriptorUsuario) {
        this.suscriptorUsuario = suscriptorUsuario;
    }

    public String getTituloSuscripcion() {
        return tituloSuscripcion;
    }

    public void setTituloSuscripcion(String tituloSuscripcion) {
        this.tituloSuscripcion = tituloSuscripcion;
    }
    
    
    
}
