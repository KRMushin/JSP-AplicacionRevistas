/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones;

import com.mycompany.proyecto_unoipc2.backend.Modelos.CarteraDigital;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioLecturaEscritura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioCarteraDigital implements RepositorioLecturaEscritura<CarteraDigital, String> {

    private Connection conn;

    public RepositorioCarteraDigital(Connection conn) {
        this.conn = conn;
    }

    @Override
    public CarteraDigital guardar(CarteraDigital modelo) throws SQLException {

        String insertQuery = "INSERT INTO carteras_digitales(usuario_representante) values (?)";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, modelo.getNombreRepresentante());
            int filasAfectadas = stmt.executeUpdate();       
            if (filasAfectadas == 0) {
                throw   new  SQLException("No se contreto el ingreso de la cartera digital");
            }
        }
        return modelo;
    }

    @Override
    public CarteraDigital actualizar(CarteraDigital modelo) throws SQLException {
        String updateQuery = " UPDATE carteras_digitales SET saldo_disponible= ? WHERE usuario_representante = ?";
        try(PreparedStatement stmt = conn.prepareStatement(updateQuery)){
            stmt.setDouble(1, modelo.getSaldoDisponible());
            stmt.setString(2, modelo.getNombreRepresentante());
             int filasAfectadas = stmt.executeUpdate();
             
             if (filasAfectadas == 0) {
                throw   new  SQLException("No se encontro un usuario para actualizar");
            }
        }
        return modelo;
    }

    @Override
    public CarteraDigital obtenerPorId(String usuarioRepresentante) throws SQLException {
        CarteraDigital cartera = null;
        String query = "SELECT * FROM carteras_digitales WHERE usuario_representante = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
             stmt.setString(1, usuarioRepresentante);
             ResultSet resultSet = stmt.executeQuery();
             
             if (resultSet.next()) {
                cartera = crearCartera(resultSet);
            }
        }
        return cartera;
    }

    private CarteraDigital crearCartera(ResultSet resultSet) throws SQLException {
            
        String representante = resultSet.getString("usuario_representante");
        Double saldoDisponible = resultSet.getDouble("saldo_disponible");
        
        return new CarteraDigital(representante,saldoDisponible);
    }

    
}
