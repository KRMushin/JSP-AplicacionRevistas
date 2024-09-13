/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Controladores;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.DatosInvalidosRevista;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioEditores;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin-mushin
 */
@WebServlet(name = "EditorRevistaServlet", urlPatterns = {"/EditorRevistaServlet"})
@MultipartConfig
public class EditorRevistaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        try {
            ServicioEditores servicioEditores = new ServicioEditores();
            if (!accion.trim().isEmpty()) {
                
                servicioEditores.guardarRevista(request, accion);
            }
            
                request.setAttribute("mensajeExito", "La publicacion de su revista ha sido un exito");
                request.getRequestDispatcher("JSP/Editores/PublicarRevista.jsp").forward(request, response);


        } catch (SQLException | DatosInvalidosRevista ex) {
  
            request.setAttribute("mensaje","No se inserto o actualizo la revista debido a que" + ex.getMessage());
            request.getRequestDispatcher("JSP/Editores/PublicarRevista.jsp").forward(request, response);
        }
    }
    
    




}
