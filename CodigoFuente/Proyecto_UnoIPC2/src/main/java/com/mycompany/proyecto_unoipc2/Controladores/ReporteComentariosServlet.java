/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Controladores;

import com.mycompany.proyecto_unoipc2.backend.Modelos.Comentario;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Revista;
import com.mycompany.proyecto_unoipc2.backend.Servicios.GeneradorReporteJasperReport;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioReportesEditor;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author kevin-mushin
 */
@WebServlet(name = "ReporteComentariosServlet", urlPatterns = {"/ReporteComentariosServlet"})
public class ReporteComentariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ServicioReportesEditor servicio = new ServicioReportesEditor();
            List<Revista> revistasDisponibles = servicio.obtenerRevistasSistema();
            
            request.setAttribute("revistasDisponibles", revistasDisponibles);
            request.getRequestDispatcher("JSP/ReportesEditores/ReporteComentarios.jsp").forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(ReporteComentariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            ServicioReportesEditor servicio = new ServicioReportesEditor();
            List<Comentario> comentariosFiltrados = servicio.filtrarComentarios(request);
            List<Revista> revistasDisponibles = servicio.obtenerRevistasSistema();
            
            request.getSession().setAttribute("comentariosFiltrados", comentariosFiltrados);
            request.setAttribute("revistasDisponibles", revistasDisponibles);
            request.setAttribute("comentariosFiltrados", comentariosFiltrados);
            request.getRequestDispatcher("JSP/ReportesEditores/ReporteComentarios.jsp").forward(request, response);
            
        } catch (SQLException | ParseException  ex) {
            Logger.getLogger(ReporteComentariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
