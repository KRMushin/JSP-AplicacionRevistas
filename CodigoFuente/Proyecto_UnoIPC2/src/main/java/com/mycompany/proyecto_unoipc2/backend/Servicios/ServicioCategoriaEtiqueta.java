/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.backend.Modelos.Categoria;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones.RepositorioCategoriaConEtiquetas;
import com.mycompany.proyecto_unoipc2.backend.Utileria.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ServicioCategoriaEtiqueta {
    
    private RepositorioCategoriaConEtiquetas repositorioCat;
    private Connection conn;

    public ServicioCategoriaEtiqueta() throws SQLException {
        this.conn = ConexionBaseDatos.getInstance();
        this.repositorioCat = new RepositorioCategoriaConEtiquetas(conn);
    }
    
    public List<Categoria> obtenerCategorias(){
        try{
            return repositorioCat.categoriasConEtiquetas();
        } catch (SQLException e) {
            e.addSuppressed(e);
        }
        return null;
    }
    
    
    
    
    
}
