/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones;

import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioLecturaEscritura;
import com.mycompany.proyecto_unoipc2.backend.Utileria.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *  repositorio que implementa escritura lectura , se conecta con la database
 * @author kevin-mushin
 */
public class RepositorioUsuario implements RepositorioLecturaEscritura<Usuario, String> {
    
    private  Connection conn;

    public RepositorioUsuario(Connection conn) {
        this.conn = conn;
    }  
    
    @Override
    public Usuario guardar(Usuario t) throws SQLException {
        
        String insertQuery = " INSERT INTO usuarios(nombre_usuario, password_usuario, rol_usuario, nombre_pila, descripcion_usuario) "
                + "values(?,?,?,?,?);";
         /* devuelve el valor de llave primario generado ya que es autoincrementable*/
         try(PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
             
             stmt.setString(1, t.getNombreUsuario());
             stmt.setString(2, t.getPassword());
             stmt.setString(3, t.getRol().toString());
             stmt.setString(4, t.getNombre());
             stmt.setString(5, t.getDescripcion());
             
             int filasAfectadas = stmt.executeUpdate();
             if (filasAfectadas == 0) {
                throw   new  SQLException("No se inserto al usuario en la Base de datos :(");
             }
         }
        return t;
    }

    @Override
    
    public Usuario actualizar(Usuario t) throws SQLException {
        
        String updateQuery = "UPDATE usuarios SET nombre_pila = ?, descripcion_usuario = ? , id_foto = ? WHERE nombre_usuario = ?";
        try(PreparedStatement stmt = conn.prepareStatement(updateQuery)){
             stmt.setString(1, t.getNombre());
             stmt.setString(2, t.getDescripcion());
             
             if (t.getFoto() != null && t.getFoto().getIdFoto() != null) {
                stmt.setLong(3, t.getFoto().getIdFoto());
            } else {
                stmt.setNull(3, java.sql.Types.BIGINT);
            }

             stmt.setString(4, t.getNombreUsuario());
             
             stmt.executeUpdate();
        }
        return t;
    }

    @Override
    public Usuario obtenerPorId(String nombreUsuario) throws SQLException {
        
        Usuario usuario = null;
        
        String query = " SELECT * FROM usuarios Where nombre_usuario = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, nombreUsuario);
            ResultSet resultSet = stmt.executeQuery();
            
           if (resultSet.next()) {
                  usuario = crearUsuario(resultSet);
            }
        }
        return usuario;
    }
    
    private Usuario crearUsuario(ResultSet rs) throws SQLException{
         
         Usuario usuario = new Usuario();
         usuario.setNombreUsuario(rs.getString("nombre_usuario"));
         usuario.setPassword(rs.getString("password_usuario"));
         usuario.setRol(Rol.valueOf(rs.getString("rol_usuario")));
         usuario.setNombre(rs.getString("nombre_pila"));
         String descripcion = rs.getString("descripcion_usuario");
         Long idFoto = rs.getLong("id_foto");
         if (descripcion != null) {
             usuario.setDescripcion(descripcion);
         }
         if (idFoto > 0) {
            usuario.getFoto().setIdFoto(idFoto);
        }
        return usuario;
    }
}
