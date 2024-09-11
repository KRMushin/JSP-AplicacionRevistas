/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Controladores;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.DatosInvalidosRegistro;
import com.mycompany.proyecto_unoipc2.backend.Excepciones.TransaccionFallidaException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.FotoUsuario;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin-mushin
 */
@WebServlet(name = "PerfilUsuarioServlet", urlPatterns = {"/PerfilUsuarioServlet"})
@MultipartConfig
public class PerfilUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(req.getParameter("idFoto"));
        
        try {
            HttpSession session = req.getSession();
            ServicioUsuario servicio = new ServicioUsuario();
            String accion = req.getParameter("action");
            System.out.println(req.getParameter("idFoto"));
            if (accion != null && accion.equalsIgnoreCase("mostrarImagen")) {
                
                String idStrFoto = req.getParameter("idFoto");
                Long idFoto = Long.valueOf(idStrFoto);
                
                System.out.println(idStrFoto + idFoto);
                response.setContentType("image/jpeg");
                
                OutputStream outPut = response.getOutputStream();
                FotoUsuario foto = servicio.procesarFotoUsuario(idFoto, outPut);
                session.setAttribute("fotoUsuario", foto);
            }
        
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al acceder a la base de datos.");
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la imagen.");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                ServicioUsuario servicioUsuario = new ServicioUsuario();
                Usuario usuario = servicioUsuario.actualizarUsuario(req);
                HttpSession session = req.getSession();
                session.setAttribute("usuario", usuario);  // Guarda el usuario actualizado en la sesión

                 req.setAttribute("actualizacionExitosa", true);  
                 req.getRequestDispatcher("JSP/PerfilUsuario.jsp").forward(req, response);
           
            } catch (DatosInvalidosRegistro | TransaccionFallidaException | ServletException | IOException | SQLException e) {
                 req.setAttribute("mensaje", "Error al actualizar el usuario: " + e.getMessage());
                 req.setAttribute("tipoMensaje", "error");
                 req.getRequestDispatcher("JSP/PerfilUsuario.jsp").forward(req, response);
   
            }
        
    }
}
