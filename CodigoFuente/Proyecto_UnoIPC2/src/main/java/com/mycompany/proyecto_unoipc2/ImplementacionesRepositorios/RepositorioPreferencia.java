/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.ImplementacionesRepositorios;

import com.mycompany.proyecto_unoipc2.Modelos.PreferenciaUsuario;
import com.mycompany.proyecto_unoipc2.Repositorios.RepositorioCRUD;
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
public class RepositorioPreferencia implements RepositorioCRUD<PreferenciaUsuario> {

    private final Connection conn;

    public RepositorioPreferencia(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<PreferenciaUsuario> listar(Long id) throws SQLException {

        List<PreferenciaUsuario> preferencias = new ArrayList<>();
        String insertQuery = " SELECT *FROM preferencias_usuario WHERE id_usuario = ? " ;
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery)){
             stmt.setLong(1, id);
             ResultSet resultSet = stmt.executeQuery();
             
             while (resultSet.next()) {
                  preferencias.add(crearPreferencia(resultSet));
            }
        }
        return preferencias;
    }

    @Override
    public PreferenciaUsuario guardar(PreferenciaUsuario modelo) throws SQLException {
        
        String insertQuery = " INSERT INTO preferencias_usuario(id_usuario,tipo_preferencia,valor_preferencia) values(?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
            stmt.setLong(1, modelo.getIdUsuario());
            stmt.setString(2, modelo.getTipoPreferencia().toString());
            stmt.setString(3, modelo.getPreferencia());
            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                try(ResultSet llaves = stmt.getGeneratedKeys()){
                     if (llaves.next()) {
                         modelo.setIdPreferencia(llaves.getLong(1));
                    }
                }
            }
        }
        return modelo;


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


    private PreferenciaUsuario crearPreferencia(ResultSet resultSet) throws SQLException {
        TipoPreferencia tipo = TipoPreferencia.valueOf(resultSet.getString("tipo_preferencia").toUpperCase());
        Long idUsuario = resultSet.getLong("id_usuario");
        String preferencia = resultSet.getString("valor_Preferencia");
        return new PreferenciaUsuario(preferencia,idUsuario,tipo);

    }

    @Override
    public PreferenciaUsuario obtenerPorId(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
    
    
}
