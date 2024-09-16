/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.backend.Repositorios.RepositorioArchivosPDF;
import com.mycompany.proyecto_unoipc2.backend.Utileria.ConexionBaseDatos;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
public class ServicioArchivosPDF {
    
    private RepositorioArchivosPDF repositorioPdf;
    private Connection conn;

    public ServicioArchivosPDF() throws SQLException {
        this.conn = ConexionBaseDatos.getInstance();
        this.repositorioPdf = new RepositorioArchivosPDF(conn);
    }
    public void mostrarPdf(HttpServletResponse response, Long idRevista) throws SQLException, IOException{
        

            
        try (InputStream streamPdf = repositorioPdf.obtenerArchivoPorId(idRevista)) {
                OutputStream salida = response.getOutputStream();

                byte[] bufferSalida = new byte[4096];
                int bytesSalida;

                while ((bytesSalida = streamPdf.read(bufferSalida)) != -1) {
                             salida.write(bufferSalida, 0, bytesSalida);
                }
                salida.flush();
            
        }
    }
}
