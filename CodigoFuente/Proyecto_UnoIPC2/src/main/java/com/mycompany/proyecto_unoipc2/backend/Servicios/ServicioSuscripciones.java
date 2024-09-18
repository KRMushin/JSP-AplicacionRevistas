/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.backend.CreadoresModelo.CreadorSuscripcion;
import com.mycompany.proyecto_unoipc2.backend.Excepciones.EstadoRevistaException;
import com.mycompany.proyecto_unoipc2.backend.Excepciones.SuscripcionInvalidaException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Revista;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Suscripcion;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioSuscripciones;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RevistaRelacionRepositorio;
import com.mycompany.proyecto_unoipc2.backend.Utileria.ConexionBaseDatos;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ServicioSuscripciones {
    
    private Connection conn;
    private RepositorioSuscripciones repositorioSuscripciones;
    private RevistaRelacionRepositorio estadosRevista;
    private CreadorSuscripcion creadorSuscripcion;
    
    public ServicioSuscripciones() throws SQLException {
        this.conn = ConexionBaseDatos.getInstance();
        this.repositorioSuscripciones = new RepositorioSuscripciones(conn);
        this.creadorSuscripcion = new CreadorSuscripcion();
        this.estadosRevista = new RevistaRelacionRepositorio(conn);
    }
    
    public Suscripcion guardarSuscripcion(HttpServletRequest request) throws SQLException, SuscripcionInvalidaException{
        Suscripcion suscripcion = creadorSuscripcion.crearYValidarSuscripcion(request);
        return repositorioSuscripciones.guardarSuscripcion(suscripcion);
    }
    
    public List<Revista> obtenerRevistasSuscriptor(String nombreUsuario) throws SQLException{
        return repositorioSuscripciones.listarSuscripcionesUsuario(nombreUsuario);
    }
    
    public void apreciarSuscripcion(String id, String nombreUsuario) throws SQLException, EstadoRevistaException {
        try {
              Long idRevista = Long.valueOf(id);
              if (!estadosRevista.esRevistaLikeable(idRevista)) {
                  throw new EstadoRevistaException("    EL AUTOR DE ESTA REVISTA A DESACTIVADO LA OPCION DE LIKES EN ESTA REVISTA");
              }
            repositorioSuscripciones.apreciarSuscripcion(idRevista,nombreUsuario);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El id no lleva el formato valido");
        }
    }
}
