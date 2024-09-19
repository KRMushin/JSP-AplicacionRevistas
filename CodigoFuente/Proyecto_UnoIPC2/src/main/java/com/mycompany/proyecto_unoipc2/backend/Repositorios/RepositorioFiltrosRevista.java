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
import java.util.Collections;
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
    
    public List<Revista> obtenerRevistasActivas(String nombreUsuario) throws SQLException {
    List<Revista> revistas = new ArrayList<>();

    String selectQuery = "SELECT r.* FROM revistas r " +
                         "LEFT JOIN suscripciones s ON r.id_revista = s.id_revista AND s.nombre_usuario = ? " +
                         "WHERE r.estado_revista = 'ACTIVA' AND s.id_revista IS NULL";

    try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
        stmt.setString(1, nombreUsuario); // Se pasa el nombre del usuario como parámetro

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            revistas.add(crearResumenRevista(rs)); // Añades cada revista a la lista
        }
    }

    return revistas;
}

    public List<Revista> obtenerRevistasPorEtiquetas(List<Long> etiquetasSeleccionadas,String nombreUsuario) throws SQLException {
        List<Revista> revistas = new ArrayList<>();

        if (etiquetasSeleccionadas == null || etiquetasSeleccionadas.isEmpty()) {
            return revistas;
        }

        StringBuilder selectQuery = new StringBuilder("SELECT DISTINCT r.id_revista, r.titulo_revista, r.nombre_autor, r.acepta_suscripciones ");
        selectQuery.append("FROM revistas r ");
        selectQuery.append("JOIN revista_etiqueta re ON r.id_revista = re.id_revista ");
        selectQuery.append("LEFT JOIN suscripciones s ON r.id_revista = s.id_revista AND s.nombre_usuario = ? ");
        selectQuery.append("WHERE re.id_etiqueta IN (");

        for (int i = 0; i < etiquetasSeleccionadas.size(); i++) {
            selectQuery.append("?");
            if (i < etiquetasSeleccionadas.size() - 1) {
                selectQuery.append(", ");
            }
        }
        selectQuery.append(") AND r.estado_revista = 'ACTIVA' AND s.id_revista IS NULL");

        try (PreparedStatement stmt = conn.prepareStatement(selectQuery.toString())) {
            stmt.setString(1, nombreUsuario);

            for (int i = 0; i < etiquetasSeleccionadas.size(); i++) {
                stmt.setLong(i + 2, etiquetasSeleccionadas.get(i)); 
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                revistas.add(crearResumenRevista(rs));        }
        }

    return revistas;
    }
    
    public List<Revista> obtenerRevistasPorCategoria(Long idCategoria, String nombreUsuario) throws SQLException {
                List<Revista> revistas = new ArrayList<>();

                String selectQuery = "SELECT r.* FROM revistas r " +
                         "LEFT JOIN suscripciones s ON r.id_revista = s.id_revista AND s.nombre_usuario = ? " +
                         "WHERE r.id_categoria = ? AND r.estado_revista = 'ACTIVA' AND s.id_revista IS NULL";
                
                try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
                    stmt.setString(1, nombreUsuario);
                    stmt.setLong(2, idCategoria);
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
            r.setAceptaSuscripciones(rs.getBoolean("acepta_suscripciones"));
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

    public List<Revista> obtenerRevistasPorCategoriaEtiquetas(Long idCategoria, String[] etiquetas, String nombreUsuario) throws SQLException {
        List<Revista> revistas = new ArrayList<>();
        return revistas;
    }

}
