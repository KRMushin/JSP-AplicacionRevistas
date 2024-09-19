/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Repositorios;

import com.mycompany.proyecto_unoipc2.backend.Modelos.Revista;
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
public class RevistaRelacionRepositorio {
    private Connection conn;

    public RevistaRelacionRepositorio(Connection conn) {
        this.conn = conn;
    }

    public void agregarCategoriaRevista(Long idRevista, Long idCategoria) throws SQLException {
        String sql = "INSERT INTO revista_categoria (id_revista, id_categoria) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, idRevista);
            ps.setLong(2, idCategoria);
            ps.executeUpdate();
        }
    }

    public void agregarEtiquetaRevista(Long idRevista, Long idEtiqueta) throws SQLException {
        String sql = "INSERT INTO revista_etiqueta (id_revista, id_etiqueta) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, idRevista);
            ps.setLong(2, idEtiqueta);
            ps.executeUpdate();
        }
    }
    
    public boolean esRevistaLikeable(Long idRevista) throws SQLException {
    String getValue = "SELECT revista_likeable FROM revistas WHERE id_revista = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(getValue)) {
        stmt.setLong(1, idRevista);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                return rs.getBoolean("revista_likeable");
                
            } else {
                    throw new SQLException("Revista no encontrada con el ID: " + idRevista);
                }
            }
        }
    }
    
    public boolean esRevistaComentable(Long idRevista) throws SQLException {
    String getValue = "SELECT revista_comentable FROM revistas WHERE id_revista = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(getValue)) {
        stmt.setLong(1, idRevista);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                return rs.getBoolean("revista_comentable");
                
            } else {
                    throw new SQLException("Revista no encontrada con el ID: " + idRevista);
                }
            }
        }
    }

    public void insertarCuadroLikes(Revista revista) throws SQLException{
                String insertLikes = "INSERT INTO likes_revistas(id_revista) values(?)";
                try(PreparedStatement ps = conn.prepareStatement(insertLikes)){
                    ps.setLong(1, revista.getIdRevista());
                    int i = ps.executeUpdate();
                    if (i <= 0) {
                        throw new SQLException("error en numeros de likes enlace");
                    }
                }
    }
    
    public List<Revista> obtenerRevistasLlaves() throws SQLException{
        List<Revista> revistas = new ArrayList<>();
        String insert = "SELECT *FROM revistas";
        try(PreparedStatement stmt = conn.prepareStatement(insert)){
             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                Revista revista = new Revista();
                revista.setTituloRevista(rs.getString("titulo_revista"));
                revista.setIdRevista(rs.getLong("id_revista"));
                revistas.add(revista);
             }
            return revistas;
        }
    }


    
    
    
    
}
