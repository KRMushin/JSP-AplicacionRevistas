/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Controladores;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.LoginInvalido;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioAutenticadorUsuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author kevin-mushin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class AutenticadorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            ServicioAutenticadorUsuario servicioAutenticador = new ServicioAutenticadorUsuario();
            Usuario usuario = servicioAutenticador.autenticarUsuario(req);
            req.setAttribute("nombreUsuario", usuario.getNombre());
            req.setAttribute("rol", usuario.getRol().toString());
            resp.sendRedirect(req.getContextPath() + "JSP/Editores/PaginaEditor.jsp");
            System.out.println("VALIDODDDDDDDDDDDDDDDD");
        } catch (SQLException ex) {
            req.setAttribute("errorDB", ex.getMessage());
            getServletContext().getRequestDispatcher("/JSP/LoginUsuario.jsp").forward(req, resp);

        } catch (LoginInvalido e) {
            req.setAttribute("errorLogin", e.getMessage());
            getServletContext().getRequestDispatcher("/JSP/LoginUsuario.jsp").forward(req, resp);
        }
    
    
    }

}
