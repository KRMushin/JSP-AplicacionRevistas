/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Modelos;

import java.io.InputStream;
import java.time.LocalDate;

/**
 *
 * @author kevin-mushin
 */
public class Revista {
    
    private Long idRevista;
    private Long idArchivoRevista;
    private Long idCategoria;
    private String categoria;
    private String tituloRevista;
    private String nombreAutor;
    private String descripcion;
    private Double costoMantenimiento;
    private LocalDate fechaCreacion;
    private InputStream pdfRevista; // archivo para almacenar el pdf
    private byte[] pdfRevistaLectura; // archivo para leer el pdf 
    private String estadoRevista;
    private boolean revistaComentable;
    private boolean revistaLikeable;
    private boolean aceptaSuscripciones;
    private int numeroLikes;

    public Revista() {
    }
    
    public Revista(Long idRevista, Long idArchivoRevisa, Long idCategoria, String categoria, String tituloRevista, String nombreAutor, Double costoMantenimiento, Double precioSuscripcion, LocalDate fechaCreacion, InputStream pdfRevista, String estadoRevista, boolean revistaComentable, boolean revistaLikeable) {
        this.idRevista = idRevista;
        this.idArchivoRevista = idArchivoRevisa;
        this.idCategoria = idCategoria;
        this.categoria = categoria;
        this.tituloRevista = tituloRevista;
        this.nombreAutor = nombreAutor;
        this.costoMantenimiento = costoMantenimiento;
        this.fechaCreacion = fechaCreacion;
        this.pdfRevista = pdfRevista;
        this.estadoRevista = estadoRevista;
        this.revistaComentable = revistaComentable;
        this.revistaLikeable = revistaLikeable;
    }

    public Long getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Long idRevista) {
        this.idRevista = idRevista;
    }

    public Long getIdArchivoRevisa() {
        return idArchivoRevista;
    }

    public void setIdArchivoRevisa(Long idArchivoRevisa) {
        this.idArchivoRevista = idArchivoRevisa;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTituloRevista() {
        return tituloRevista;
    }

    public void setTituloRevista(String tituloRevista) {
        this.tituloRevista = tituloRevista;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCostoMantenimiento() {
        return costoMantenimiento;
    }

    public void setCostoMantenimiento(Double costoMantenimiento) {
        this.costoMantenimiento = costoMantenimiento;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public InputStream getPdfRevista() {
        return pdfRevista;
    }

    public void setPdfRevista(InputStream pdfRevista) {
        this.pdfRevista = pdfRevista;
    }

    public byte[] getPdfRevistaLectura() {
        return pdfRevistaLectura;
    }

    public void setPdfRevistaLectura(byte[] pdfRevistaLectura) {
        this.pdfRevistaLectura = pdfRevistaLectura;
    }

    public String getEstadoRevista() {
        return estadoRevista;
    }

    public void setEstadoRevista(String estadoRevista) {
        this.estadoRevista = estadoRevista;
    }

    public boolean isRevistaComentable() {
        return revistaComentable;
    }

    public void setRevistaComentable(boolean revistaComentable) {
        this.revistaComentable = revistaComentable;
    }

    public boolean isRevistaLikeable() {
        return revistaLikeable;
    }

    public void setRevistaLikeable(boolean revistaLikeable) {
        this.revistaLikeable = revistaLikeable;
    }

    public int getNumeroLikes() {
        return numeroLikes;
    }

    public void setNumeroLikes(int numeroLikes) {
        this.numeroLikes = numeroLikes;
    }

    public boolean isAceptaSuscripciones() {
        return aceptaSuscripciones;
    }

    public void setAceptaSuscripciones(boolean aceptaSuscripciones) {
        this.aceptaSuscripciones = aceptaSuscripciones;
    }
    
    
    
    
    public boolean esValida() {
        this.toString();
    return tituloRevista != null && !tituloRevista.isEmpty()
        && nombreAutor != null && !nombreAutor.isEmpty()
        && fechaCreacion != null
        && costoMantenimiento != null && costoMantenimiento >= 0
        && estadoRevista != null && !estadoRevista.isEmpty()
        && idCategoria != null && idCategoria > 0;
}

    @Override
    public String toString() {
        return "Revista{" + "idRevista=" + idRevista + ", idArchivoRevista=" + idArchivoRevista + ", idCategoria=" + idCategoria + ", categoria=" + categoria + ", tituloRevista=" + tituloRevista + ", nombreAutor=" + nombreAutor + ", descripcion=" + descripcion + ", costoMantenimiento=" + costoMantenimiento + ", fechaCreacion=" + fechaCreacion + ", pdfRevista=" + pdfRevista + ", pdfRevistaLectura=" + pdfRevistaLectura + ", estadoRevista=" + estadoRevista + ", revistaComentable=" + revistaComentable + ", revistaLikeable=" + revistaLikeable + '}';
    }
    


    
    
    
    
    
    
}
