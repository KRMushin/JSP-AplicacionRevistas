/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Utileria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ConexionBaseDatos {
    
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/app_revistas";
    private static final String USER = "root";
    private static final String PASSWORD = "mushin2022";
    //instancia perteneciente a la clase conexion base de datos, solo existe una de estas
    private static Connection connection;
    
    // constructor privador para evitar instanciacion de la clase esto hace que solo se use una connexion
    private ConexionBaseDatos(){
    }
    //punto de acceso a la conexion
    public static Connection getInstance() throws SQLException {
    if (connection == null || connection.isClosed()) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver JDBC.", e);
        } catch (SQLException e) {
            throw e;
        }
    }
    return connection;
}

   
}
/*
     public static Connection getInstance() throws SQLException {
        // verifica si la conexion esta nula o cerrada , si si esta la crea
        if (connection == null || connection.isClosed()) {
            System.out.println("    CONEXION ESTABLECIDA");
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
        }
        //rettorna la conexion global 
        return connection;
    }

*/