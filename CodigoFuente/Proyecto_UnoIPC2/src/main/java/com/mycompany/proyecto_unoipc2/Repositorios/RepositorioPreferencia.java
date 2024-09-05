/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Repositorios;

import com.mycompany.proyecto_unoipc2.Modelos.PreferenciaUsuario;
import com.mycompany.proyecto_unoipc2.Utileria.TipoPreferencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioPreferencia implements RepositorioCRUD<PreferenciaUsuario, String, String> {

    private Connection conn;

    public RepositorioPreferencia(Connection conn) {
        this.conn = conn;
    }

    
    
    @Override
    public List<PreferenciaUsuario> listar(String nombreUsuario, String parametro) throws SQLException {

        List<PreferenciaUsuario> preferencias = new ArrayList<>();
        String insertQuery = " SELECT *FROM preferencias_usuario WHERE nombre_usuario = ? AND tipo_preferencia = ? " ;
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
             stmt.setString(1, nombreUsuario);
             stmt.setString(2, parametro);
             ResultSet resultSet = stmt.executeQuery();
             
             while (resultSet.next()) {
                  preferencias.add(crearPreferencia(resultSet));
            }
        }
        return preferencias;
    }

    @Override
    public PreferenciaUsuario guardar(PreferenciaUsuario preferenciaUsuario) throws SQLException {
        
        String insertQuery = " INSERT INTO preferencias_usuario(nombre_usuario,tipo_preferencia,valor_preferencia) values(?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, preferenciaUsuario.getNombreUsuario());
            stmt.setString(2, preferenciaUsuario.getTipoPreferencia().toString());
            stmt.setString(3, preferenciaUsuario.getPreferencia());
            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                try(ResultSet llaves = stmt.getGeneratedKeys()){
                     if (llaves.next()) {
                         preferenciaUsuario.setIdPreferencia(llaves.getLong(1));
                    }
                }
            }
        }
        return preferenciaUsuario;
    }

    @Override
    public PreferenciaUsuario actualizar(PreferenciaUsuario modelo) throws SQLException {
        
        String insertQuery = "UPDATE preferencias_usuario SET valor_preferencia = ? WHERE id_preferencia = ?";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
             stmt.setString(1, modelo.getPreferencia());
             stmt.setLong(2, modelo.getIdPreferencia());
             stmt.executeUpdate();
        }
        return modelo;
    }

    @Override
    public PreferenciaUsuario obtenerPorId(Long id) throws SQLException {
        PreferenciaUsuario preferenciaUsuario = null;
        String query = "SELECT * FROM preferencias_usuario WHERE id_preferencia = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                preferenciaUsuario = crearPreferencia(resultSet);
            }
        }

        return preferenciaUsuario;
    }
    @Override
    public void eliminar(Long id) throws SQLException {
        String deleteQuery = "DELETE FROM preferencias_usuario WHERE id_preferencia = ?";
        try (PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private PreferenciaUsuario crearPreferencia(ResultSet resultSet) throws SQLException {
        TipoPreferencia tipo = TipoPreferencia.valueOf(resultSet.getString("tipo_preferencia").toUpperCase());
        String nombreUsuario = resultSet.getString("nombre_usuario");
        String preferencia = resultSet.getString("valor_Preferencia");
        return new PreferenciaUsuario(preferencia,nombreUsuario,tipo);

    }


    
    
    
    
    
}
