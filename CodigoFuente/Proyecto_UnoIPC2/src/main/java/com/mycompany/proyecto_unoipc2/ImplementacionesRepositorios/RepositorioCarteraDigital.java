/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.ImplementacionesRepositorios;

import com.mycompany.proyecto_unoipc2.Modelos.CarteraDigital;
import com.mycompany.proyecto_unoipc2.Repositorios.RepositorioLecturaEscritura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioCarteraDigital implements RepositorioLecturaEscritura<CarteraDigital> {

    private Connection conn;

    public RepositorioCarteraDigital(Connection conn) {
        this.conn = conn;
    }
    
    
    @Override
    public CarteraDigital guardar(CarteraDigital modelor) throws SQLException {
        String insertQuery = "INSERT INTO carteras_digitales(id_usuario) values (?)";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
            stmt.setLong(1, modelor.getIdUsuario());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                try(ResultSet llave = stmt.getGeneratedKeys()){
                     if (llave.next()) {
                         modelor.setIdCartera(llave.getLong(1));
                    }
                }
            }
        
        }
        return modelor;


    }

    @Override
    public CarteraDigital actualizar(CarteraDigital modelo) throws SQLException {
        String updateQuery = " UPDATE carteras_digitales SET saldo_dispoible WHERE id_usuario = ?";
        try(PreparedStatement stmt = conn.prepareStatement(updateQuery)){
             stmt.setLong(1, modelo.getIdUsuario());
             stmt.executeUpdate();
        }
        return modelo;
        
            


    }

    @Override
    public CarteraDigital obtenerPorId(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
