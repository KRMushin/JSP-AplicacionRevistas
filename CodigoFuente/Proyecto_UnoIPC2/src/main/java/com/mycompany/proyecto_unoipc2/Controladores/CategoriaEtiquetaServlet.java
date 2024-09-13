/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.Controladores;

import com.mycompany.proyecto_unoipc2.backend.Modelos.Categoria;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Etiqueta;
import com.mycompany.proyecto_unoipc2.backend.Repositorios.Implementaciones.RepositorioCategoriaConEtiquetas;
import com.mycompany.proyecto_unoipc2.backend.Servicios.ServicioCategoriaEtiqueta;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
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
@WebServlet(name = "CategoriaEtiquetaServlet", urlPatterns = {"/CategoriaEtiquetaServlet"})
public class CategoriaEtiquetaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String accion = request.getParameter("accion");
        try {
            
            if (accion.equalsIgnoreCase("obtenerCategorias")) {
                ServicioCategoriaEtiqueta cat = new ServicioCategoriaEtiqueta();
                List<Categoria> categorias = cat.obtenerCategorias();
                
                HttpSession session = request.getSession();
                session.setAttribute("categorias", categorias);
                
                request.setAttribute("categorias", categorias);
                getServletContext().getRequestDispatcher("/JSP/Editores/PublicarRevista.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            request.setAttribute("errorCategorias", ex);
            getServletContext().getRequestDispatcher("/JSP/Editores/PublicarRevista.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}

//                for (Categoria categoria : categorias) {
//}
//    if (categoria.getIdCategoria() == 1) {
//        System.out.println(categoria.getNombreCategoria() + "id"+categoria.getIdCategoria());
//
//        List<Etiqueta> etiquetas = categoria.getEtiquetas();
//        if (etiquetas != null) {
//            for (Etiqueta etiqueta : etiquetas) {
//                if (etiqueta != null) {
//                    System.out.println(etiqueta.getEtiqueta() + "id"+etiqueta.getIdEtiqueta());
//                }
//            }
//        }
//    }