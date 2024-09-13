/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones;

import com.mycompany.proyecto_unoipc2.backend.Modelos.Categoria;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Etiqueta;
import com.mycompany.proyecto_unoipc2.backend.Utileria.ConexionBaseDatos;
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
public class RepositorioCategoriaConEtiquetas {
    
    private Connection conn;

    public RepositorioCategoriaConEtiquetas(Connection conn) throws SQLException {
        this.conn = conn;
    }
    
    public List<Categoria> categoriasConEtiquetas() throws SQLException{
         List<Categoria> categorias = new ArrayList<>();
         String obtnerCategorias = "SELECT * FROM categorias";
        try(PreparedStatement stmt = conn.prepareStatement(obtnerCategorias)){
              ResultSet rs = stmt.executeQuery();
        
           while (rs.next()) {
               Categoria categoria = crearCategoria(rs);
               categoria.setEtiquetas(obtenerEtiquetas(categoria.getIdCategoria()));
               categorias.add(categoria);
           }
         }
        return categorias;
        
    }

    private Categoria crearCategoria(ResultSet rs) throws SQLException {
        Long idCategoria = rs.getLong("id_categoria");
        String nombreCategoria = rs.getString("nombre_categoria");
    return new Categoria(idCategoria, nombreCategoria, new ArrayList<>());
}


    private List<Etiqueta> obtenerEtiquetas(Long idCategoria) throws SQLException {
            List<Etiqueta> etiquetas = new ArrayList<>();

            String queryEtiquetas = "SELECT e.id_etiqueta, e.nombre_etiqueta from etiquetas e JOIN categoria_etiqueta ce ON e.id_etiqueta = ce.id_etiqueta WHERE ce.id_categoria = ?";

            try (PreparedStatement statementEtiquetas = conn.prepareStatement(queryEtiquetas)) {
                statementEtiquetas.setLong(1, idCategoria);
                
                try (ResultSet resultSetEtiquetas = statementEtiquetas.executeQuery()) {
                    while (resultSetEtiquetas.next()) {
                        Long idEtiqueta = resultSetEtiquetas.getLong("id_etiqueta");
                        String nombreEtiqueta = resultSetEtiquetas.getString("nombre_etiqueta");

                        Etiqueta etiqueta = new Etiqueta(idEtiqueta, nombreEtiqueta);
                        etiquetas.add(etiqueta);
                    }
                }
            }
            return etiquetas;
    }
}
