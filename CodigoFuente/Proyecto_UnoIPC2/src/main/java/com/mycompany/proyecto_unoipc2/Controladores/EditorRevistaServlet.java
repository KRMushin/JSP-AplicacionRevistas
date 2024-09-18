/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Controladores;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.DatosInvalidosRevista;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Revista;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioRevistas;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
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
        String accion = request.getParameter("accion");

            try {
                    ServicioRevistas servicioEditores = new ServicioRevistas();
                        if (accion.equalsIgnoreCase("obtenerRevistas")) {
                            HttpSession session = request.getSession();
                            Usuario usuario = (Usuario) session.getAttribute("usuario");
                            List<Revista> revistasAsociadas = servicioEditores.obtenerRevistasAsociadas(usuario.getNombreUsuario());

                            session.setAttribute("revistasAsociadas", revistasAsociadas);
                            getServletContext().getRequestDispatcher("/JSP/VisualizarRevistas.jsp").forward(request, response);

                        }else if (accion.equalsIgnoreCase("visualizarRevista")) {

                            Revista revistaActualizar = servicioEditores.obtenerPorId(request);
                            request.setAttribute("revistaVisualizar", revistaActualizar);
                            getServletContext().getRequestDispatcher("/JSP/EditoresSuscriptores/VisualizadorDetallesRevista.jsp").forward(request, response);
                }
            } catch (SQLException | DatosInvalidosRevista ex) {
                Logger.getLogger(EditorRevistaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String accion = request.getParameter("accion");
            
            if (accion != null) {
                    if (accion.equalsIgnoreCase("publicacion")) {
                        publicarRevista(request, response);
                    } else if (accion.equalsIgnoreCase("actualizarRevista")) {
                        actualizarRevista(request, response);
                    }
            } else {
                    request.setAttribute("mensaje", "Acción no especificada");
                    request.getRequestDispatcher("JSP/Editores/PublicarRevista.jsp").forward(request, response);
            }
    }
        private void publicarRevista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                try {
                    ServicioRevistas servicioEditores = new ServicioRevistas();

                    servicioEditores.guardarRevista(request);
                    request.setAttribute("mensajeExito", "La publicación de su revista ha sido un éxito");
                    request.getRequestDispatcher("JSP/Editores/PublicarRevista.jsp").forward(request, response);
                } catch (SQLException | DatosInvalidosRevista ex) {
                    request.setAttribute("mensaje", "No se pudo publicar la revista debido a: " + ex.getMessage());
                    request.getRequestDispatcher("JSP/Editores/PublicarRevista.jsp").forward(request, response);
                }
        }

        private void actualizarRevista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    try {
                        ServicioRevistas servicioEditores = new ServicioRevistas();

                        servicioEditores.actualizarRevista(request);
                        request.setAttribute("mensajeResultado", "La actualización de su revista ha sido un éxito");
                        request.getRequestDispatcher("JSP/EditoresSuscriptores/VisualizadorDetallesRevista.jsp").forward(request, response);

                    } catch (SQLException | DatosInvalidosRevista ex) {
                        request.setAttribute("mensajeResultado", "No se pudo actualizar la revista debido a: " + ex.getMessage());
                        request.getRequestDispatcher("JSP/EditoresSuscriptores/VisualizadorDetallesRevistajsp").forward(request, response);
                    }
        }
    }