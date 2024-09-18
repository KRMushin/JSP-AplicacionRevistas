/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Controladores;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.FiltrosInvalidosBusqueda;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Revista;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioNavegacionRevistas;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin-mushin
 */
@WebServlet(name = "NavegadorRevistasServlet", urlPatterns = {"/NavegadorRevistasServlet"})
public class NavegadorRevistasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            ServicioNavegacionRevistas servicio = new ServicioNavegacionRevistas();
            List<Revista> revistasEncontradas = servicio.obtenerRevistasPorFiltros(request);
            
            request.setAttribute("revistasEncontradas", revistasEncontradas);
            getServletContext().getRequestDispatcher("/JSP/EditoresSuscriptores/NavegadorRevistasFiltros.jsp").forward(request, response);

        } catch (SQLException | FiltrosInvalidosBusqueda ex) {
            Logger.getLogger(NavegadorRevistasServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        
        
        
    }
}