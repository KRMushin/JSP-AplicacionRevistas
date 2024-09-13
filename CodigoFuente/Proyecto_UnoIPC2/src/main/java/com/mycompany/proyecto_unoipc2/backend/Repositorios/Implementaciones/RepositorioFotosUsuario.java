/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones;

import com.mycompany.proyecto_unoipc2.backend.Modelos.FotoUsuario;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioLecturaEscritura;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioFotosUsuario implements RepositorioLecturaEscritura<FotoUsuario,Long>{

    private Connection connection;

    public RepositorioFotosUsuario(Connection connection) {
        this.connection = connection;
    }

    @Override
    public FotoUsuario guardar(FotoUsuario modelo) throws SQLException {
        String query = "INSERT INTO fotos_usuario (foto_usuario, foto) values(?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, modelo.getNombreUsuario());
            stmt.setBlob(2, modelo.getFoto());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No se pudo guardar la foto, no se afectaron filas.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long idFoto = generatedKeys.getLong(1);
                    modelo.setIdFoto(idFoto);
                    System.out.println("Foto guardada con ID: " + idFoto);
                    return modelo;
                } else {
                    throw new SQLException("Guardar foto falló, no se obtuvo ID.");
                }
            }
        }
    }

    @Override
    public FotoUsuario actualizar(FotoUsuario modelo) throws SQLException {
         String query = "UPDATE fotos_usuario SET foto = ? WHERE id_foto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
              stmt.setBlob(1, modelo.getFoto());
              stmt.setLong(2, modelo.getIdFoto());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No se pudo actualizar la foto, no se afectaron filas.");
            }
            return modelo;
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar la foto: " + e.getMessage());
        }
    }

    @Override
    public FotoUsuario obtenerPorId(Long id) throws SQLException {
         String query = "SELECT * FROM fotos_usuario WHERE id_foto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
              stmt.setLong(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    
                    return crearFotoUsuario(resultSet);
                } else {
                    throw new SQLException("No se encontró una foto con el ID proporcionado.");
                }
            }
        }
    }

    private FotoUsuario crearFotoUsuario(ResultSet rs) throws SQLException {
        FotoUsuario foto = new FotoUsuario();
            foto.setIdFoto(rs.getLong("id_foto"));
            foto.setNombreUsuario(rs.getString("foto_usuario"));
        
            Blob blob = rs.getBlob("foto");
            if (blob != null) {
                foto.setFoto(blob.getBinaryStream());
            } else {
                foto.setFoto(null); 
            }
        
        return foto;
    }
}
