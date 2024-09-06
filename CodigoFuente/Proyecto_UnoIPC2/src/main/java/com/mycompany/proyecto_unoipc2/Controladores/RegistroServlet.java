/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Controladores;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.DatosInvalidosRegistro;
import com.mycompany.proyecto_unoipc2.backend.Excepciones.TransaccionFallidaException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioRegistro;
import com.mycompany.proyecto_unoipc2.backend.Utileria.Rol;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin-mushin
 */
@MultipartConfig
@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})

public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
                ServicioRegistro servicioRegistro = new ServicioRegistro();
                Usuario usuarioCreado = servicioRegistro.RegistrarUsuario(req);
                req.setAttribute("mensaje", "Usuario creado exitosamente: " + usuarioCreado.getNombreUsuario());
                req.getSession().setAttribute("mensaje", "Usuario creado exitosamente: " + usuarioCreado.getNombreUsuario());
                resp.sendRedirect(req.getContextPath() +"/JSP/RegistroExitoso.jsp");

        } catch (DatosInvalidosRegistro | TransaccionFallidaException | SQLException e) {
            req.setAttribute("Error_datos_invalidos", e.getMessage().toString());
            getServletContext().getRequestDispatcher("/JSP/RegistroUsuario.jsp").forward(req, resp);

        }
    }
}