/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Modelos;

import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class Categoria {
    
    private Long idCategoria;
    private String nombreCategoria;
    private List<Etiqueta> etiquetas;

    public Categoria() {
    }

    public Categoria(Long idCategoria, String nombreCategoria, List<Etiqueta> etiquetas) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.etiquetas = etiquetas;
    }
    public boolean esCategoriaValida() {
        if (this.nombreCategoria != null && !this.nombreCategoria.isEmpty()) {
            return this.nombreCategoria.matches("[a-zA-Z]+");
        }
        return false; 
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }
    
    
}
