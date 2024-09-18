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
public class Comentario {
    
    private Long idComentario;
    private Long idRevista;
    private String nombreUsuario;
    private LocalDate fechaComentario;
    private String comentario;

    public Comentario() {
    }

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public LocalDate getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(LocalDate fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    @Override
    public String toString() {
        return "Comentario{" + "idComentario=" + idComentario + ", idRevista=" + idRevista + ", nombreUsuario=" + nombreUsuario + ", fechaComentario=" + fechaComentario + ", comentario=" + comentario + '}';
    }
    
    
    
    
}
