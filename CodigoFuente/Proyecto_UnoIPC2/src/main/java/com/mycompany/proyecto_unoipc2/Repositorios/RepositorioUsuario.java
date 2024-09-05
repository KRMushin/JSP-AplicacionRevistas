/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Repositorios;

import com.mycompany.proyecto_unoipc2.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.Utileria.Rol;
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
        
        String insertQuery = " INSERT INTO usuarios(nombre_usuario, password_usuario, rol_usuario, nombre_pila, descripcion_usuario, foto) "
                + "values(?,?,?,?,?,?);";
         /* devuelve el valor de llave primario generado ya que es autoincrementable*/
         try(PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
             
             stmt.setString(1, t.getNombreUsuario());
             stmt.setString(2, t.getPassword());
             stmt.setString(3, t.getRol().toString());
             stmt.setString(4, t.getNombre());
             stmt.setString(5, t.getDescripcion());
             stmt.setBytes(6, t.getFoto());
             int filasAfectadas = stmt.executeUpdate();
             if (filasAfectadas == 0) {
                throw   new  SQLException("No se inserto al usuario en la Base de datos :(");
             }
         }
        return t;
    }

    @Override
    
    public Usuario actualizar(Usuario t) throws SQLException {
        
        String updateQuery = "UPDATE usuarios SET nombre_pila = ?, descripcion_usuario = ? , foto = ? WHERE nombre_usuario = ?";
        try(PreparedStatement stmt = conn.prepareStatement(updateQuery)){
             stmt.setString(1, t.getNombre());
             stmt.setString(2, t.getDescripcion());
             stmt.setBytes(3, t.getFoto());
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
            
            while (resultSet.next()) {
                  usuario = crearUsuario(resultSet);
            }
        }
        return usuario;
    }
    
    private Usuario crearUsuario(ResultSet rs) throws SQLException{
         
         String nombreUsuario = rs.getString("nombre_usuario");
         String password = rs.getString("password_usuario");
         Rol rol = Rol.valueOf(rs.getString("rol_usuario"));
         String nombrePila = rs.getString("nombre_pila");
         String descripcion = rs.getString("descripcion_usuario");
         
         Usuario usuario = new Usuario(nombreUsuario,password,rol,nombrePila);
         if (descripcion != null) {
             usuario.setDescripcion(rs.getString("descripcion_usuario"));
        }

         byte[] foto = rs.getBytes("foto");
        if (foto != null) {
            usuario.setFoto(foto);
        }
        return usuario;
    }
}
