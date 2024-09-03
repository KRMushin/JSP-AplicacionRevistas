/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.ImplementacionesRepositorios;

import com.mycompany.proyecto_unoipc2.Modelos.PreferenciaUsuario;
import com.mycompany.proyecto_unoipc2.Repositorios.RepositorioCRUD;
import jakarta.jms.Connection;
import java.sql.SQLException;
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
    public List<PreferenciaUsuario> listar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PreferenciaUsuario guardar(PreferenciaUsuario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PreferenciaUsuario actualizar(PreferenciaUsuario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PreferenciaUsuario porId(String t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
