/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Controladores;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.SuscripcionInvalidaException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Revista;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Suscripcion;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioSuscripciones;
import java.io.IOException;
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
@WebServlet(name = "SuscripcionesServlet", urlPatterns = {"/SuscripcionesServlet"})
public class SuscripcionesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ServicioSuscripciones servicioSuscripciones = new ServicioSuscripciones();
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            List<Revista> revistasSuscripcion= servicioSuscripciones.obtenerRevistasSuscriptor(usuario.getNombreUsuario());
            
            request.setAttribute("revistasAsociadas", revistasSuscripcion);
            getServletContext().getRequestDispatcher("/JSP/VisualizarRevistas.jsp").forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(SuscripcionesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ServicioSuscripciones servicio = new ServicioSuscripciones();
            Suscripcion suscripcion = servicio.guardarSuscripcion(request);
            
            request.setAttribute("mensajeServicio", "Su suscripcion se ha logrado con exito, para visualizar la revista dirijase al apartado de suscripciones");
            request.getRequestDispatcher("/JSP/EditoresSuscriptores/NavegadorRevistasFiltros.jsp").forward(request, response);
        
        } catch (SQLException | SuscripcionInvalidaException ex) {
            
            request.setAttribute("mensajeServicio", ex.getMessage());
            request.getRequestDispatcher("/JSP/Suscriptores/SuscripcionRevista.jsp").forward(request, response);
        
        }
    }
}
