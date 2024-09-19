/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.Servicios;

import com.mycompany.proyecto_unoipc2.backend.Modelos.Comentario;
import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author kevin-mushin
 */
public class GeneradorReporteJasperReport {
    
  
    public void generarReporteComentario(List<Comentario> comentariosFiltrados, HttpServletResponse response, String reportePath) throws JRException {

        JasperReport jasperReport = JasperCompileManager.compileReport(reportePath);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(comentariosFiltrados);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Reporte de Comentarios");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=ReporteComentarios.pdf");

        try (OutputStream outStream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
            outStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
