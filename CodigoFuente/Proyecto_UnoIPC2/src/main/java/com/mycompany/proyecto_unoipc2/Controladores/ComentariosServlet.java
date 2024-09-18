/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Controladores;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.ComentarioInvalidoException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Comentario;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioComentarios;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.console;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin-mushin
 */
@WebServlet(name = "ComentariosServlet", urlPatterns = {"/ComentariosServlet"})
public class ComentariosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idRevista = request.getParameter("idRevista");

            ServicioComentarios servicioComentarios = new ServicioComentarios();
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            List<Comentario> comentariosUsuario = servicioComentarios.obtenerComentariosAsociados(usuario.getNombreUsuario(), idRevista);

            request.setAttribute("comentariosUsuario", comentariosUsuario);
            request.setAttribute("revistaComentable", servicioComentarios.esRevistaComentable(idRevista));
            request.setAttribute("idRevista", request.getParameter("idRevista"));
            request.getRequestDispatcher("JSP/Suscriptores/ComentarRevistas.jsp").forward(request, response);

        } catch (SQLException |ComentarioInvalidoException ex) {
            request.setAttribute("comentariosUsuario", "error");
            request.getRequestDispatcher("JSP/Suscriptores/ComentarRevistas.jsp");
        } 
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idRevista = request.getParameter("idRevista");
            ServicioComentarios servicioComentarios = new ServicioComentarios();
            Comentario comentario = servicioComentarios.extraerYValidarComentario(request);
            
            List<Comentario> comentariosUsuario = servicioComentarios.obtenerComentariosAsociados(request.getParameter("nombreUsuario"),idRevista);
            
            request.setAttribute("comentariosUsuario", comentariosUsuario);
            request.setAttribute("respuestaServidor", " El comentario se inserto correctamente");
            request.setAttribute("revistaComentable", servicioComentarios.esRevistaComentable(idRevista));
            request.setAttribute("idRevista",request.getParameter("idRevista"));
            request.getRequestDispatcher("/JSP/Suscriptores/ComentarRevistas.jsp").forward(request, response);
            
        } catch (SQLException | ComentarioInvalidoException ex) {
            request.setAttribute("respuestaServidor", ex);
            request.getRequestDispatcher("/JSP/Suscriptores/ComentarRevistas.jsp").forward(request, response);
        }
            
        
    }

}
