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
public class RepositorioFiltrosRevista {
        private Connection conn;

    public RepositorioFiltrosRevista(Connection conn) {
        this.conn = conn;
    }
    
    public List<Revista> obtenerRevistasActivas() throws SQLException {
        List<Revista> revistas = new ArrayList<>();
        
        String insertQuery = "SELECT *FROM revistas WHERE estado_revista = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
            stmt.setString(1, "ACTIVA");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                revistas.add(crearResumenRevista(rs));
            }
        }
        return revistas;
    }
    public List<Revista> obtenerRevistasPorEtiquetas(List<Long> identificadores) throws SQLException {
           List<Revista> revs = new ArrayList<>();
                for (Long id : identificadores) {
                    Revista revista = obtenerPorId(id);
                    if (revista != null) {
                        revs.add(revista);
                    }
                }
        return revs;
    }
    
    public List<Revista> obtenerRevistasPorCategoria(Long idCategoria) throws SQLException {
                List<Revista> revistas = new ArrayList<>();
                String selectQuery = "SELECT * FROM revistas WHERE id_categoria = ? AND estado_revista = 'ACTIVA'";
                try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
                    stmt.setLong(1, idCategoria);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        revistas.add(crearResumenRevista(rs));
                    }
                }
                return revistas;
    }
    private Revista crearResumenRevista(ResultSet rs) throws SQLException {
            Revista r = new Revista();
            r.setIdRevista(rs.getLong("id_revista"));
            r.setTituloRevista(rs.getString("titulo_revista"));
            r.setNombreAutor(rs.getString("nombre_autor"));
            return r;
    }

    public Revista obtenerPorId(Long id) throws SQLException {
        String selectQuery = "SELECT id_revista, titulo_revista, nombre_autor FROM revistas WHERE id_revista = ? AND estado_revista = 'ACTIVA'";
        try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
                    stmt.setLong(1, id);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        return crearResumenRevista(rs);
                    }
                }
        return null;

    }

}
