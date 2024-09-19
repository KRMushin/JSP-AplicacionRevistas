/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Repositorios;

import com.mycompany.proyecto_unoipc2.backend.Modelos.Comentario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioReportesEditor {
    
    private Connection conn;

    public RepositorioReportesEditor(Connection conn) {
        this.conn = conn;
    }
    
    public List<Comentario> listarComentariosPorFechas(Date fechaInicio, Date fechaFin) throws SQLException {
            
        List<Comentario> comentarios = new ArrayList<>();
    
            String insertSql = "SELECT c.id_revista, c.comentario, c.id_comentario, c.nombre_usuario, c.fecha_comentario, r.titulo_revista, r.titulo_revista AS nombre_revista FROM comentarios c JOIN revistas r ON c.id_revista = r.id_revista "
                    + "WHERE c.fecha_comentario BETWEEN ? AND ? ORDER BY c.fecha_comentario DESC";
    
            try(PreparedStatement stmt = conn.prepareStatement(insertSql)){
                 stmt.setDate(1, fechaInicio);
                 stmt.setDate(2, fechaFin);

                     ResultSet rs = stmt.executeQuery();
                         while (rs.next()) {
                            comentarios.add(crearComentario(rs));
                        }
            }
        return comentarios;
    }
    
    public List<Comentario> listarPorFechaInicio(Date fechaInicio) throws SQLException{
            List<Comentario> comentarios = new ArrayList<>();

            String insertQuery = "SELECT c.id_revista, c.comentario, c.id_comentario, c.nombre_usuario, c.fecha_comentario, r.titulo_revista, r.titulo_revista AS nombre_revista FROM comentarios c JOIN revistas r ON c.id_revista = r.id_revista "
                    + "WHERE c.fecha_comentario >= ? ORDER BY c.fecha_comentario DESC";
            try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
                     stmt.setDate(1, fechaInicio);
                         ResultSet rs = stmt.executeQuery();
                             while (rs.next()) {
                                comentarios.add(crearComentario(rs));
                            }
                }
           return comentarios;
    
    }
    
    public List<Comentario> listarComentariosHastaFecha(Date fechaFin) throws SQLException {
            List<Comentario> comentarios = new ArrayList<>();
            String insertQuery = "SELECT c.id_revista, c.comentario, c.id_comentario, c.nombre_usuario, c.fecha_comentario, r.titulo_revista, r.titulo_revista AS nombre_revista FROM comentarios c JOIN revistas r ON c.id_revista = r.id_revista "
                    + "WHERE c.fecha_comentario <= ? ORDER BY c.fecha_comentario DESC;";
            
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
                     stmt.setDate(1, fechaFin);
                         ResultSet rs = stmt.executeQuery();
                             while (rs.next()) {
                                comentarios.add(crearComentario(rs));
                            }
                }
        return comentarios;
    }

    public List<Comentario> listarTodosLosComentarios() throws SQLException {
        List<Comentario> comentarios = new ArrayList<>();
        String insertQuery = "SELECT c.*, r.titulo_revista FROM comentarios c JOIN revistas r ON c.id_revista = r.id_revista";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                comentarios.add(crearComentario(rs));
            }
        }
        return comentarios;

    }
     public List<Comentario> listarComentariosRevista(Long id) throws SQLException{
        List<Comentario> comentariosAsociados = new ArrayList<>();
        
        String insertQuery = "SELECT comentarios.*, revistas.titulo_revista FROM comentarios JOIN revistas ON comentarios.id_revista = revistas.id_revista WHERE comentarios.id_revista = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
             stmt.setLong(1, id);
             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                 comentariosAsociados.add(crearComentario(rs));
            }
        } 
    
    return comentariosAsociados;
    }
    
    private Comentario crearComentario(ResultSet rs) throws SQLException {
        Comentario comentario = new Comentario();
        
        comentario.setIdComentario(rs.getLong("id_comentario"));
        comentario.setComentario(rs.getString("comentario"));
        comentario.setNombreUsuario(rs.getString("nombre_usuario"));
        comentario.setIdRevista(rs.getLong("id_revista"));
        comentario.setTituloRevista(rs.getString("titulo_revista"));
        comentario.setFechaComentario(rs.getDate("fecha_comentario").toLocalDate());

        return comentario;
    }

}
