/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones;

import com.mycompany.proyecto_unoipc2.backend.Modelos.Anuncio;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAnuncios implements RepositorioCRUD<Anuncio, String> {
    
    private Connection conn;

    public RepositorioAnuncios(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Anuncio> listar(String nombreUsuario) throws SQLException {
        List<Anuncio> anuncios = new ArrayList<>();
        
        String selectQuery = "SELECT * FROM anuncios WHERE nombre_usuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                anuncios.add(crearAnuncio(rs));
            }
        }
        return anuncios;
    }

    @Override
    public Anuncio guardar(Anuncio modelo) throws SQLException {
        String insertQuery = "INSERT INTO anuncios (nombre_usuario, tipo_anuncio) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, modelo.getNombreAnunciante());
//            stmt.setString(2, modelo.getAnuncio());
            stmt.executeUpdate();

            // Obtener la clave generada (id_anuncio)
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
//                modelo.setId(generatedKeys.getLong(1));
            }
        }
        return modelo;
    }

    @Override
    public Anuncio actualizar(Anuncio modelo) throws SQLException {
        String updateQuery = "UPDATE anuncios SET nombre_usuario = ?, tipo_anuncio = ? WHERE id_anuncio = ?";
        try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
//            stmt.setString(1, modelo.getNombreUsuario());
//            stmt.setString(2, modelo.getTipoAnuncio());
//            stmt.setLong(3, modelo.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
//                throw new SQLException("No se pudo actualizar el anuncio con id: " + modelo.getId());
            }
        }
        return modelo;
    }

    @Override
    public Anuncio obtenerPorId(Long identificador) throws SQLException {
        String selectQuery = "SELECT * FROM anuncios WHERE id_anuncio = ?";
        Anuncio anuncio = null;
        try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
            stmt.setLong(1, identificador);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                anuncio = crearAnuncio(rs);
            }
        }
        return anuncio;
    }

    @Override
    public void eliminar(String nombreUsuario) throws SQLException {
        String deleteQuery = "DELETE FROM anuncios WHERE id_anuncio = ?";
        try (PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
//            stmt.setLong(1, identificador);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
//                throw new SQLException("No se pudo eliminar el anuncio con id: " + identificador);
            }
        }
    }

    private Anuncio crearAnuncio(ResultSet rs) throws SQLException {
        long idAnuncio = rs.getLong("id_anuncio");
        String nombreUsuario = rs.getString("nombre_usuario");
        String tipoAnuncio = rs.getString("tipo_anuncio");
        Timestamp fechaCreacion = rs.getTimestamp("fecha_creacion");
        return null;
//        return new Anuncio(idAnuncio, nombreUsuario, tipoAnuncio, fechaCreacion.toString());
    }
}

