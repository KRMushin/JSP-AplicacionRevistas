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
    public CarteraDigital guardar(CarteraDigital modelo) throws SQLException {
        String insertQuery = "INSERT INTO carteras_digitales(id_usuario) values (?)";
        try(PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
            stmt.setLong(1, modelo.getIdUsuario());
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                try(ResultSet llave = stmt.getGeneratedKeys()){
                     if (llave.next()) {
                         modelo.setIdCartera(llave.getLong(1));
                    }
                }
            }       
        }
        return modelo;


    }

    @Override
    public CarteraDigital actualizar(CarteraDigital modelo) throws SQLException {
        String updateQuery = " UPDATE carteras_digitales SET saldo_disponible= ? WHERE id_usuario = ?";
        try(PreparedStatement stmt = conn.prepareStatement(updateQuery)){
            stmt.setDouble(1, modelo.getSaldoDisponible());
            stmt.setLong(2, modelo.getIdUsuario());
             int filasAfectadas = stmt.executeUpdate();
             
             if (filasAfectadas == 0) {
                throw   new  SQLException("No se encontro un usuario para actualizar");
            }
        }
        return modelo;
    }

    @Override
    public CarteraDigital obtenerPorId(Long id) throws SQLException {
        CarteraDigital cartera = null;
        String query = "SELECT * FROM carteras_digitales WHERE id_usuario = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
             stmt.setLong(1, id);
             ResultSet resultSet = stmt.executeQuery();
             
             if (resultSet.next()) {
                cartera = crearCartera(resultSet);
            }
        }
        return cartera;

    }

    private CarteraDigital crearCartera(ResultSet resultSet) throws SQLException {
            
            Long idCartera = resultSet.getLong("id_cartera");
            Double saldoDisponible = resultSet.getDouble("saldo_disponible");
            Long idUsuario = resultSet.getLong("id_usuario");
            return new CarteraDigital(idCartera,saldoDisponible,idUsuario);
    }
    
}
