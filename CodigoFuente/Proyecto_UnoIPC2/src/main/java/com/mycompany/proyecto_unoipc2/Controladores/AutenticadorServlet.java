/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Controladores;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.LoginInvalido;
import com.mycompany.proyecto_unoipc2.backend.Excepciones.TransaccionFallidaException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Categoria;
import com.mycompany.proyecto_unoipc2.backend.Modelos.PreferenciaUsuario;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioAutenticadorUsuario;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioCategoriaEtiqueta;
import com.mycompany.proyecto_unoipc2.backend.Utileria.OpcionesUsuario;
import com.mycompany.proyecto_unoipc2.backend.Utileria.TipoOpciones;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

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
            ServicioCategoriaEtiqueta cat = new ServicioCategoriaEtiqueta();
            List<OpcionesUsuario> opcionesMenu = TipoOpciones.valueOf(usuario.getRol().toString()).obtenerOpcionesRol();
           
            List<PreferenciaUsuario> preferencias = usuario.getPreferencias();
            List<Categoria> categorias = cat.obtenerCategorias();
            HttpSession session = req.getSession();
            
            System.out.println(categorias.size());
            session.setAttribute("usuario", usuario);
            session.setAttribute("menuOpciones", opcionesMenu);
            session.setAttribute("categorias", categorias);

            // Reenviar al JSP `PaginaPrincipal.jsp`
            RequestDispatcher dispatcher = req.getRequestDispatcher("/JSP/PaginaPrincipal.jsp");
            dispatcher.forward(req, resp);
            
        } catch (SQLException | TransaccionFallidaException ex) {
            req.setAttribute("errorDB", ex.getMessage());
            getServletContext().getRequestDispatcher("/JSP/LoginUsuario.jsp").forward(req, resp);

        } catch (LoginInvalido e) {
            req.setAttribute("errorLogin", e.getMessage());
            getServletContext().getRequestDispatcher("/JSP/LoginUsuario.jsp").forward(req, resp);
        }
    
    
    }

}
