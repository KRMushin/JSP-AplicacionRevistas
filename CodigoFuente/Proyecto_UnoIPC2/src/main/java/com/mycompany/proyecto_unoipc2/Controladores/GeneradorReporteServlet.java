/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Controladores;

import com.mycompany.proyecto_unoipc2.backend.Modelos.Comentario;
import com.mycompany.proyecto_unoipc2.backend.Servicios.GeneradorReporteJasperReport;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author kevin-mushin
 */
@WebServlet(name = "GeneradorReporteServlet", urlPatterns = {"/GeneradorReporteServlet"})
public class GeneradorReporteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            // Obtener los comentarios filtrados desde la sesión
        HttpSession session = request.getSession();
        List<Comentario> comentariosFiltrados = (List<Comentario>) session.getAttribute("comentariosFiltrados");

        // Obtener el contexto de la aplicación para acceder a la ruta del archivo .jrxml
        String reportePath = getServletContext().getRealPath("/WEB-INF/ReporteComentarios.jrxml");

        // Generar el reporte PDF usando JasperReports
        try {
            GeneradorReporteJasperReport generadorReporte = new GeneradorReporteJasperReport();
            generadorReporte.generarReporteComentario(comentariosFiltrados, response, reportePath);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
