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
public class AnuncioRepositorio implements RepositorioCRUD<Anuncio, String> {
    
    private Connection connection;

    public AnuncioRepositorio(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Anuncio> listar(String nombreUsuario) throws SQLException {
        List<Anuncio> anuncios = new ArrayList<>();
        String sql = "SELECT * FROM anuncios WHERE nombreAnunciante = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Anuncio anuncio = new Anuncio();
                    anuncio.setNombreAnunciante(rs.getString("nombreAnunciante"));
                    anuncio.setAnuncio(rs.getBinaryStream("anuncio"));
                    anuncios.add(anuncio);
                }
            }
        }
        return anuncios;
    }

    @Override
    public Anuncio guardar(Anuncio anuncio) throws SQLException {
        String sql = "INSERT INTO anuncios (nombreAnunciante, anuncio) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, anuncio.getNombreAnunciante());
            ps.setBinaryStream(2, anuncio.getAnuncio());
            ps.executeUpdate();
        }
        return anuncio;
    }

    @Override
    public Anuncio actualizar(Anuncio anuncio) throws SQLException {
        String sql = "UPDATE anuncios SET anuncio = ? WHERE nombreAnunciante = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBinaryStream(1, anuncio.getAnuncio());
            ps.setString(2, anuncio.getNombreAnunciante());
            ps.executeUpdate();
        }
        return anuncio;
    }

    @Override
public Anuncio obtenerPorId(Long id) throws SQLException {
    Anuncio anuncio = null;
    String sql = "SELECT * FROM anuncios WHERE id = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setLong(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                anuncio = new Anuncio();
                anuncio.setNombreAnunciante(rs.getString("nombreAnunciante"));
                anuncio.setAnuncio(rs.getBinaryStream("anuncio"));
            }
        }
    }
    return anuncio;
}

    @Override
    public void eliminar(String nombreAnunciante) throws SQLException {
        String sql = "DELETE FROM anuncios WHERE nombreAnunciante = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombreAnunciante);
            ps.executeUpdate();
        }
    }

}
