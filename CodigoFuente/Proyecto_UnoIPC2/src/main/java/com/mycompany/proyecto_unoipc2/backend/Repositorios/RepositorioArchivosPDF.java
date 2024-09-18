/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Repositorios;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class RepositorioArchivosPDF {

    private final Connection conn;

    public RepositorioArchivosPDF(Connection conn) {
        this.conn = conn;
    }
    
    public Long guardarArchivoPDF(InputStream archivoPDF) throws SQLException {
        
        String insertPDF = "INSERT INTO ARCHIVOS_REVISTA (archivo) VALUES (?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(insertPDF, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setBlob(1, archivoPDF);
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("No se pudo guardar el archivo PDF.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID del archivo PDF.");
                }
            }
        }
    }
    public InputStream obtenerArchivoPorId(Long idArchivoPDF) throws SQLException {
        
        String query = "SELECT archivo FROM ARCHIVOS_REVISTA WHERE id_archivo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, idArchivoPDF);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBlob("archivo").getBinaryStream();
                }
            }
        }
        return null; 
    }
}
