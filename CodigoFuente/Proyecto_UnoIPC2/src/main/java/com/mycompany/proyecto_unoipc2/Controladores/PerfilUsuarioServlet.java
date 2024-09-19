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
        try {
            ServicioUsuario servicio = new ServicioUsuario();
            Usuario llaveUsuario = (Usuario) req.getSession().getAttribute("usuario");
            String accion = req.getParameter("action");

            if (accion != null && accion.equalsIgnoreCase("mostrarImagen")) {
                    servicio.mostrarFotoPerfil(req,response);
                    
            }else if (accion != null && accion.equalsIgnoreCase("perfilUsuario")) {
                
                Usuario usuario = servicio.obtenerUsuario(llaveUsuario.getNombreUsuario());
                String editar = req.getParameter("edit");
                boolean isEditable = editar != null && editar.equalsIgnoreCase("true");
                
                req.setAttribute("usuarioPerfil", usuario);
                req.setAttribute("isEditable", isEditable);

                getServletContext().getRequestDispatcher("/JSP/PerfilUsuario.jsp").forward(req, response);
            }
        } catch (SQLException | IOException | TransaccionFallidaException e) {
                 req.setAttribute("mensaje", "Error en la obtencion del usuario");
                 req.getRequestDispatcher("JSP/PerfilUsuario.jsp").forward(req, response);
        } 
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                ServicioUsuario servicioUsuario = new ServicioUsuario();
                Usuario usuario = servicioUsuario.actualizarUsuario(req);
                HttpSession session = req.getSession();
                session.setAttribute("usuario", usuario); 

                 req.setAttribute("actualizacionExitosa", true);
                 req.setAttribute("usuarioPerfil", usuario);
                 req.setAttribute("isEditable", true);
                 req.setAttribute("mensaje", "La actualizacion del perfil ha sido exitosa");
                 req.getRequestDispatcher("JSP/PerfilUsuario.jsp").forward(req, response);

            } catch (DatosInvalidosRegistro | TransaccionFallidaException | ServletException | IOException | SQLException e) {
                 req.setAttribute("mensaje", "Error al actualizar el usuario: " + e.getMessage());
                 req.setAttribute("tipoMensaje", "error");
                 req.getRequestDispatcher("JSP/PerfilUsuario.jsp").forward(req, response);
   
            }
        
    }
}
