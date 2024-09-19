/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.ComentarioInvalidoException;
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
public class RepositorioComentario {
    
   private Connection conn;

    public RepositorioComentario(Connection conn) {
        this.conn = conn;
    }

    public List<Comentario> listarComentariosUsuario(String nombreUsuario, Long id) throws SQLException{
        List<Comentario> comentariosAsociados = new ArrayList<>();
        
        String insertQuery = "SELECT *FROM comentarios WHERE nombre_usuario = ? AND id_revista = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
             stmt.setString(1, nombreUsuario);
             stmt.setLong(2, id);
             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                 comentariosAsociados.add(crearComentario(rs));
            }
        } 
    
    return comentariosAsociados;
    }
    public Comentario guardarComentario(Comentario comentario) throws SQLException, ComentarioInvalidoException{
        
        String insertModel = "INSERT INTO comentarios(nombre_usuario,id_revista,fecha_comentario,comentario) VALUES(?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(insertModel, PreparedStatement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, comentario.getNombreUsuario());
            stmt.setLong(2, comentario.getIdRevista());
            stmt.setDate(3, Date.valueOf(comentario.getFechaComentario()));
            stmt.setString(4, comentario.getComentario());
            
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas <= 0) {
                throw new ComentarioInvalidoException("La insecion del comentario no fue efectuada revise los datos del cometario porfaavor");
            }
             
             try(ResultSet id = stmt.getGeneratedKeys()){
                  if (id.next()) {
                        Long idGenerado = id.getLong(1);
                        comentario.setIdComentario(idGenerado);
                  }else {
                      throw new SQLException("Guardar comentario falló, no se obtuvo ID.");
                  }
             }
            return comentario;
        }
    }
    public Comentario obtenerPorId(Long id) throws SQLException, ComentarioInvalidoException{
        
        String insertQuery = "SELECT *FROM comentarios WHERE id_comentario = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return crearComentario(rs);
            }else{
                throw new ComentarioInvalidoException("El comentario buscado no existe con el id proporcionado");
            }
            
        } 
    }
    public List<Comentario> listarComentariosRevista(Long id) throws SQLException{
        List<Comentario> comentariosAsociados = new ArrayList<>();
        
        String insertQuery = "SELECT *FROM comentarios WHERE nombre_usuario = ? AND id_revista = ?";
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
        comentario.setFechaComentario(rs.getDate("fecha_comentario").toLocalDate());

        return comentario;
    }
   
   
    
}
