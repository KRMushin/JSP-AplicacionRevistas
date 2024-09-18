/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Controladores;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.EstadoRevistaException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioSuscripciones;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin-mushin
 */
@WebServlet(name = "LikesRevistaServlet", urlPatterns = {"/LikesRevistaServlet"})
public class LikesRevistaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try {
            ServicioSuscripciones servicio = new ServicioSuscripciones();
            
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            servicio.apreciarSuscripcion(request.getParameter("idRevista"),usuario.getNombreUsuario());
            response.getWriter().write("¡Le has dado like a la revista!");
        } catch (SQLException ex) {
            response.getWriter().write("Error al dar like: " + ex.getMessage());
        } catch (EstadoRevistaException ex) {
            response.getWriter().write(ex.getMessage());
        }
    }
}
