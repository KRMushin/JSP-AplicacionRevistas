/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.backend.CreadoresModelo.CreadorComentario;
import com.mycompany.proyecto_unoipc2.backend.Excepciones.ComentarioInvalidoException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Comentario;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones.RepositorioComentario;
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
public class ServicioComentarios {
    
    private RepositorioComentario repositorioComentarios;
    private CreadorComentario creadorComentario;
    private RevistaRelacionRepositorio revistaEstados;
    private Connection conn;

    public ServicioComentarios() throws SQLException {
        this.conn = ConexionBaseDatos.getInstance();
        this.creadorComentario = new CreadorComentario();
        this.repositorioComentarios = new RepositorioComentario(conn);
        this.revistaEstados = new RevistaRelacionRepositorio(conn);
        
    }
    
    public Comentario extraerYValidarComentario(HttpServletRequest request) throws SQLException, ComentarioInvalidoException{
         Comentario comentario = creadorComentario.validarYCrearComentario(request);
         return repositorioComentarios.guardarComentario(comentario);
    }
    
    public List<Comentario> obtenerComentariosAsociados(String nombreUsuario, String idRevista) throws SQLException, ComentarioInvalidoException{
        try {
            Long id = Long.valueOf(idRevista);
            return repositorioComentarios.listarComentariosUsuario(nombreUsuario,id);
        } catch (NumberFormatException e) {
            throw new ComentarioInvalidoException("Id invalido para la operacion");
        }
    }
    
    public boolean esRevistaComentable(String idRevista) throws ComentarioInvalidoException{
        try {
            Long id = Long.valueOf(idRevista);
            return revistaEstados.esRevistaComentable(id);
        } catch (NumberFormatException | SQLException e) {
            throw new ComentarioInvalidoException("verifique el id para la validacion correcta del comentario");
        }
    }
    
    
    
    
    
}
