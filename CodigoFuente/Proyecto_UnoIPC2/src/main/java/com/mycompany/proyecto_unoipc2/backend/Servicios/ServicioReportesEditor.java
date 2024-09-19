/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.backend.Modelos.Comentario;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Revista;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioReportesEditor;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RevistaRelacionRepositorio;
import com.mycompany.proyecto_unoipc2.backend.Utileria.ConexionBaseDatos;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ServicioReportesEditor {
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private RepositorioReportesEditor reportesEditor;
    private RevistaRelacionRepositorio revistaRelacionRepositorio;
    private Connection conn;

    public ServicioReportesEditor() throws SQLException {
        this.conn = ConexionBaseDatos.getInstance();
        this.reportesEditor = new RepositorioReportesEditor(conn);
        this.revistaRelacionRepositorio = new RevistaRelacionRepositorio(conn);
    }
    
    public List<Comentario> filtrarComentarios(HttpServletRequest request) throws SQLException, ParseException{
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("filtrarFechas")) {
            return comentariosPorFechas(request);
        }else if (accion.equalsIgnoreCase("filtrarRevista")) {
            String idRevista = request.getParameter("idRevista");
            if (idRevista == null || idRevista.isEmpty()) {
                return null;
            }
            Long idR = Long.valueOf(idRevista);
            return reportesEditor.listarComentariosRevista(idR);
        }
    return null;
    }
    private List<Comentario> comentariosPorFechas(HttpServletRequest request) throws SQLException, ParseException{
         
         List<Comentario> comentariosFiltrados = new ArrayList<>();
         
         String fechaInicio = request.getParameter("fechaInicio");
         String fechaFin = request.getParameter("fechaFin");
         
         System.out.println(fechaInicio);
         if ((fechaInicio != null && !fechaInicio.isEmpty()) && (fechaFin != null && !fechaFin.isEmpty())) {
             System.out.println("111");
                return filtrarPorIntervalos(fechaInicio, fechaFin);
            } else if ((fechaInicio == null || fechaInicio.isEmpty()) && (fechaFin != null && !fechaFin.isEmpty())) {
                System.out.println("2222");
                return filtrarPorFechaFin(fechaFin);
            } else if ((fechaInicio != null && !fechaInicio.isEmpty()) && (fechaFin == null || fechaFin.isEmpty())) {
                System.out.println(fechaInicio + " si correcto ");
                System.out.println("3333");
                return filtrarPorFechaInicio(fechaInicio);
            } else if((fechaInicio == null || fechaInicio.isEmpty()) && (fechaFin == null || fechaFin.isEmpty())){
                System.out.println("4444");
                return obtenerTodosLosComentarios();
            }

                return null;

    }
    
    public List<Revista> obtenerRevistasSistema() throws SQLException{
        return revistaRelacionRepositorio.obtenerRevistasLlaves();
    }
    private List<Comentario> filtrarPorIntervalos(String fechaI, String fechaFin) throws SQLException, ParseException {
        return reportesEditor.listarComentariosPorFechas(validarFecha(fechaI), validarFecha(fechaFin));
    }

    private List<Comentario> filtrarPorFechaInicio(String fechaInicio) throws ParseException, SQLException {
        return reportesEditor.listarPorFechaInicio(validarFecha(fechaInicio));
    }

    private List<Comentario> filtrarPorFechaFin(String fechaFin) throws ParseException, SQLException {
        return reportesEditor.listarComentariosHastaFecha(validarFecha(fechaFin));
    }

    private List<Comentario> obtenerTodosLosComentarios() throws SQLException {
        return reportesEditor.listarTodosLosComentarios();
    }
    
    
    
    private Date validarFecha(String fechaEntrada) {

    try {
        // Convertir directamente la cadena a java.sql.Date
        return java.sql.Date.valueOf(fechaEntrada);
    } catch (IllegalArgumentException e) {
        // Si el formato de la fecha es incorrecto, arroja una excepción
        throw new IllegalArgumentException("El formato de la fecha es inválido o ha sido manipulado: " + fechaEntrada);
    }
}


    
}
